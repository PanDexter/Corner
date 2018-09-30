package szeptunm.corner.dataaccess.repository

import android.annotation.SuppressLint
import io.reactivex.Observable
import io.reactivex.SingleTransformer
import io.reactivex.schedulers.Schedulers
import szeptunm.corner.BuildConfig
import szeptunm.corner.dataaccess.api.model.StandingResponse
import szeptunm.corner.dataaccess.api.service.StandingService
import szeptunm.corner.dataaccess.database.dao.StandingDao
import szeptunm.corner.dataaccess.database.entity.StandingEntity
import szeptunm.corner.entity.Standing
import timber.log.Timber
import javax.inject.Inject

class StandingRepository @Inject constructor(
        private var standingDao: StandingDao, private var standingService: StandingService) {

    private val standingTransformer: SingleTransformer<List<StandingEntity>, List<Standing>> =
            SingleTransformer { upstream ->
                upstream.flattenAsObservable { list -> list }
                        .map { Standing(it) }
                        .toList()
            }

    fun getAllStandings(): Observable<List<Standing>> {
        return Observable.concatArray(
                getStandingsFromDb(), getStandingFromApi()
        )
    }

    private fun getStandingsFromDb(): Observable<List<Standing>> {
        return standingDao.getAllStandings()
                .compose(standingTransformer)
                .filter { it.isNotEmpty() }
                .toObservable()
                .doOnNext {
                    Timber.d("Dispatching ${it.size} standings from DB...")

                }
    }

    private fun getStandingFromApi(): Observable<List<Standing>> {
        return standingService.getStandingById(BuildConfig.MATCH_KEY, 2014)
                .map {
                    mapStandingToEntity(it)
                }
                .doOnSuccess {
                    saveToDatabase(it)
                }
                .compose(standingTransformer)
                .toObservable()
    }

    private fun mapStandingToEntity(standingResponse: StandingResponse): List<StandingEntity> {
        val standingList: MutableList<StandingEntity> = ArrayList()
        standingResponse.standings.map { standingInfo ->
            standingInfo.table.map {
                with(it) {
                    if (standingInfo.type == "TOTAL") {
                        standingList.add(
                                StandingEntity(
                                        currentMatchDay = standingResponse.season.currentMatchday,
                                        position = position,
                                        playedGames = playedGames,
                                        won = won,
                                        draw = draw,
                                        lost = lost,
                                        points = points,
                                        goalsDifference = goalDifference,
                                        crestUrl = team.crestUrl,
                                        teamId = team.id,
                                        competitionId = standingResponse.competition.id))
                    }
                }
            }
        }
        return standingList
    }

    @SuppressLint("CheckResult")
    private fun saveToDatabase(standingList: List<StandingEntity>) {
        Observable.fromCallable { standingDao.insertAllStandings(standingList) }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe {
                    Timber.d("Insert ${standingList.size} standings to DB...")
                }
    }
}