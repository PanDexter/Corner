package szeptunm.corner.dataaccess.repository

import android.annotation.SuppressLint
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Observable.*
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
        return concatArray(
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
                .flatMapCompletable { matchResponse ->
                    mapTeamAndCompetitionAndSave(matchResponse)
                    //mapMatchToEntity(matchResponse)
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
                                homeTeamGoalFull = it.score.fullTime.homeTeam,
                                awayTeamGoalFull = it.score.fullTime.awayTeam,
                                homeTeamGoalExtra = it.score.extraTime.homeTeam,
                                awayTeamGoalExtra = it.score.extraTime.awayTeam,
                                homePenalties = it.score.penalties.homeTeam,
                                awayPenalties = it.score.penalties.awayTeam,
                                homeTeam = it.homeTeam.name,
                                awayTeam = it.awayTeam.name,
                                date = it.utcDate, competition = it.competition.name))
            }
        }
        return matchList
    }

    private fun mapTeamAndCompetitionAndSave(matchResponse: MatchResponse): Completable {
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

        return saveTeamsAndCompetitionsToDatabase(teamList, competitionList)
    }

    @SuppressLint("CheckResult")
    private fun saveMatchesToDatabase(matchList: List<MatchEntity>) {
        fromCallable { matchDao.insertAllMatches(matchList) }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe {
                    Timber.d("Insert ${matchList.size} match to DB...")
                }
    }

    @SuppressLint("CheckResult")
    private fun saveTeamsAndCompetitionsToDatabase(teamList: List<TeamEntity>,
            competitionList: List<CompetitionEntity>): Completable {
        return Completable.fromCallable {
            teamDao.insertAllTeams(teamList)
            competitionDao.insertAllCompetitions(competitionList)
        }
    }
}