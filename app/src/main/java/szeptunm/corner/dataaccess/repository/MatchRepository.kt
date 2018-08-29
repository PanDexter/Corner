package szeptunm.corner.dataaccess.repository

import android.annotation.SuppressLint
import io.reactivex.Observable
import io.reactivex.SingleTransformer
import io.reactivex.schedulers.Schedulers
import szeptunm.corner.dataaccess.api.model.MatchResponse
import szeptunm.corner.dataaccess.api.service.MatchService
import szeptunm.corner.dataaccess.database.dao.CompetitionDao
import szeptunm.corner.dataaccess.database.dao.MatchDao
import szeptunm.corner.dataaccess.database.dao.TeamDao
import szeptunm.corner.dataaccess.database.entity.CompetitionEntity
import szeptunm.corner.dataaccess.database.entity.MatchEntity
import szeptunm.corner.dataaccess.database.entity.TeamEntity
import szeptunm.corner.entity.Match
import timber.log.Timber
import javax.inject.Inject

class MatchRepository @Inject constructor(
        private var matchDao: MatchDao, private var teamDao: TeamDao, private var competitionDao: CompetitionDao,
        private var matchService: MatchService) {


    private val matchTransformer: SingleTransformer<List<MatchEntity>, List<Match>> =
            SingleTransformer { upstream ->
                upstream.flattenAsObservable { list -> list }
                        .map { Match(it) }
                        .toList()
            }

    fun getAllMatches(): Observable<List<Match>> {
        return Observable.concatArray(
                getMatchesFromDb(), getMatchesFromApi()
        )
    }

    private fun getMatchesFromDb(): Observable<List<Match>> {
        return matchDao.getAllMatches()
                .compose(matchTransformer)
                .filter { it.isNotEmpty() }
                .toObservable()
                .doOnNext {
                    Timber.d("Dispatching ${it.size} news from DB...")
                }
    }

    private fun getMatchesFromApi(): Observable<List<Match>> {
        return matchService.getAllMatches(10)
                .map { matchResponse ->
                    mapTeamAndCompetitionAndSave(matchResponse)
                    mapMatchToEntity(matchResponse)
                }
                .doOnSuccess {
                    saveMatchesToDatabase(it)
                }
                .compose(matchTransformer)
                .toObservable()
    }

    private fun mapMatchToEntity(matchResponse: MatchResponse): List<MatchEntity> {
        val matchList: MutableList<MatchEntity> = ArrayList()
        for (i in 0 until matchResponse.matches.size) {
            matchResponse.matches[i].let {
                matchList.add(
                        MatchEntity(
                                it.score.fullTime.homeTeam,
                                it.score.fullTime.awayTeam,
                                it.score.extraTime.homeTeam,
                                it.score.extraTime.awayTeam,
                                it.score.penalties.homeTeam,
                                it.score.penalties.awayTeam,
                                it.homeTeam.name,
                                it.awayTeam.name,
                                it.utcDate, it.competition.name))
            }
        }
        return matchList
    }

    private fun mapTeamAndCompetitionAndSave(matchResponse: MatchResponse) {
        val teamList: MutableList<TeamEntity> = ArrayList()
        val competitionList: MutableList<CompetitionEntity> = ArrayList()
        for (i in 0 until matchResponse.matches.size) {
            matchResponse.matches[i].let {
                teamList.addAll(
                        arrayOf(
                                TeamEntity(it.homeTeam.id, it.homeTeam.name),
                                TeamEntity(it.awayTeam.id, it.awayTeam.name)))
                competitionList.add(CompetitionEntity(it.competition.id, it.competition.name))
            }
        }

        saveTeamsAndCompetitionsToDatabase(teamList, competitionList)
    }

    @SuppressLint("CheckResult")
    private fun saveMatchesToDatabase(matchList: List<MatchEntity>) {
        Observable.fromCallable { matchDao.insertAllMatches(matchList) }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe {
                    Timber.d("Insert ${matchList.size} match to DB...")
                }
    }

    @SuppressLint("CheckResult")
    private fun saveTeamsAndCompetitionsToDatabase(teamList: List<TeamEntity>,
            competitionList: List<CompetitionEntity>) {
        Observable.fromCallable {
            teamDao.insertAllTeams(teamList)
            competitionDao.insertAllCompetitions(competitionList)
        }
                .subscribeOn(Schedulers.computation())
                .observeOn(Schedulers.computation())
                .subscribe {
                    Timber.d("Insert ${teamList.size} teams to DB...")
                    Timber.d("Insert ${competitionList.size} competitions to DB...")
                }
    }
}