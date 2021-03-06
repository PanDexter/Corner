package szeptunm.corner.dataaccess.repository

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.SingleTransformer
import szeptunm.corner.BuildConfig
import szeptunm.corner.dataaccess.api.model.MatchResponse
import szeptunm.corner.dataaccess.api.service.MatchService
import szeptunm.corner.dataaccess.database.dao.CompetitionDao
import szeptunm.corner.dataaccess.database.dao.MatchDao
import szeptunm.corner.dataaccess.database.dao.TeamDao
import szeptunm.corner.dataaccess.database.entity.CompetitionEntity
import szeptunm.corner.dataaccess.database.entity.MatchEntity
import szeptunm.corner.dataaccess.database.entity.TeamEntity
import szeptunm.corner.entity.ClubInfo
import szeptunm.corner.entity.Competition
import szeptunm.corner.entity.Match
import szeptunm.corner.entity.Team
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

    fun getAllMatches(clubInfo: ClubInfo): Observable<List<Match>> = getMatchesFromDb(clubInfo)

    private fun getMatchesFromDb(clubInfo: ClubInfo): Observable<List<Match>> =
            matchDao.getMatchByTeamId(clubInfo.matchTeamId)
                    .compose(matchTransformer)
                    .filter { it.isNotEmpty() }
                    .toObservable()
                    .doOnNext {
                        Timber.d("Dispatching ${it.size} news from DB...")
                    }

    fun getMatchesFromApi(clubInfo: ClubInfo): Completable =
            matchService.getAllMatches(BuildConfig.MATCH_KEY, clubInfo.matchTeamId)
                    .flatMapCompletable { matchResponse ->
                        mapMatchToEntities(matchResponse)
                    }

    private fun mapMatchToEntities(matchResponse: MatchResponse): Completable {
        val teamList: MutableList<TeamEntity> = ArrayList()
        val competitionList: MutableList<CompetitionEntity> = ArrayList()
        val matchList: MutableList<MatchEntity> = ArrayList()
        matchResponse.matches.map {
            matchList.add(
                    MatchEntity(
                            homeTeamGoalFull = it.score.fullTime.homeTeam,
                            awayTeamGoalFull = it.score.fullTime.awayTeam,
                            homeTeamGoalExtra = it.score.extraTime.homeTeam,
                            awayTeamGoalExtra = it.score.extraTime.awayTeam,
                            homePenalties = it.score.penalties.homeTeam,
                            awayPenalties = it.score.penalties.awayTeam,
                            homeTeam = it.homeTeam.id,
                            awayTeam = it.awayTeam.id,
                            matchDate = it.utcDate,
                            competition = it.competition.id))
            teamList.addAll(
                    arrayOf(
                            TeamEntity(it.homeTeam.id, it.homeTeam.name),
                            TeamEntity(it.awayTeam.id, it.awayTeam.name)))
            competitionList.add(CompetitionEntity(it.competition.id, it.competition.name))
        }
        return saveToDatabase(teamList, competitionList, matchList)
    }

    private fun saveToDatabase(teamList: List<TeamEntity>,
            competitionList: List<CompetitionEntity>, matchList: List<MatchEntity>): Completable =
            Completable.fromAction {
                teamDao.insertAllTeams(teamList)
                competitionDao.insertAllCompetitions(competitionList)
                matchDao.insertAllMatches(matchList)
            }

    fun getTeamById(id: Int): Single<Team> =
            teamDao.getTeamById(id)
                    .map { it ->
                        Team(it)
                    }

    fun getCompetitionById(id: Int): Single<Competition> =
            competitionDao.getCompetitionById(id)
                    .map { it ->
                        Competition(it)
                    }
}