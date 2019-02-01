package szeptunm.corner.dataaccess.repository

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.SingleTransformer
import szeptunm.corner.BuildConfig
import szeptunm.corner.dataaccess.api.model.StandingResponse
import szeptunm.corner.dataaccess.api.service.StandingService
import szeptunm.corner.dataaccess.database.dao.StandingDao
import szeptunm.corner.dataaccess.database.entity.StandingEntity
import szeptunm.corner.entity.ClubInfo
import szeptunm.corner.entity.Standing
import timber.log.Timber
import java.util.concurrent.TimeUnit.SECONDS
import javax.inject.Inject

class StandingRepository @Inject constructor(
        private var standingDao: StandingDao, private var standingService: StandingService) {

    private val standingTransformer: SingleTransformer<List<StandingEntity>, List<Standing>> =
            SingleTransformer { upstream ->
                upstream.flattenAsObservable { list -> list }
                        .map { Standing(it) }
                        .toList()
            }

    fun getAllStandings(clubInfo: ClubInfo): Observable<List<Standing>> =
            getStandingsFromDb(clubInfo)

    private fun getStandingsFromDb(clubInfo: ClubInfo): Observable<List<Standing>> =
            standingDao.getStandingByCompetition(clubInfo.competitionId)
                    .compose(standingTransformer)
                    .filter { it.isNotEmpty() }
                    .toObservable()
                    .doOnNext {
                        Timber.d("Dispatching ${it.size} standings from DB...")

                    }

    fun getStandingFromApi(clubInfo: ClubInfo): Completable =
            standingService.getStandingById(BuildConfig.MATCH_KEY, clubInfo.competitionId)
                    .timeout(10, SECONDS)
                    .flatMapCompletable {
                        mapStandingToEntity(it)
                    }

    private fun mapStandingToEntity(standingResponse: StandingResponse): Completable {
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
                                        teamId = team.id,
                                        competitionId = standingResponse.competition.id))
                    }
                }
            }
        }
        return saveToDatabase(standingList)
    }

    private fun saveToDatabase(standingList: List<StandingEntity>): Completable =
        Completable.fromAction {
            standingDao.insertAllStandings(standingList)
        }
}