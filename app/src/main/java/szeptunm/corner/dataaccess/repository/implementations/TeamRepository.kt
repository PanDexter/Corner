package szeptunm.corner.dataaccess.repository.implementations

import io.reactivex.Single
import io.reactivex.SingleSource
import szeptunm.corner.dataaccess.api.service.TeamService
import szeptunm.corner.dataaccess.database.DatabaseTransaction
import szeptunm.corner.dataaccess.database.dao.TeamDao
import szeptunm.corner.dataaccess.database.entity.TeamEntity
import javax.inject.Inject
import io.reactivex.SingleTransformer
import szeptunm.corner.entity.Team
import io.reactivex.Completable
import szeptunm.corner.commons.Utils
import szeptunm.corner.dataaccess.database.CornerDatabase

class TeamRepository @Inject constructor(var teamDao: TeamDao, var teamService: TeamService,
        var cornerDatabase: CornerDatabase, var utils: Utils) {

    val hasConnection = utils.isConnectedToInternet()

    private fun teamTransformer(): SingleTransformer<List<TeamEntity>, List<Team>> {
        return SingleTransformer { upstream ->
            upstream.flattenAsObservable { list -> list }
                    .map { Team(it) }
                    .toList()
        }
    }

    private val teamEntityTransformer = teamTransformer()

//
//    fun getTeamById(id: Int): Single<List<Team>> = teamDao.getTeamById(id).compose(teamEntityTransformer)
//
//    fun getAllTeams(): Single<List<Team>> = teamDao.getAllTeams().compose(teamEntityTransformer)
//
//    fun updateTeamDb(){
//        return teamService.getAllTeams().map()
//    }
//
//    fun refreshTeam(){
//        if(teamDao.getAllTeams() ==null){
//            teamService.getAllTeams().compose(teamEntityTransformer)
//        }
//    }
////
//    fun getTeamsFromApi():Single<List<TeamEntity>>{
//        return teamService.getAllTeams()
//                .doOnSuccess {
//                    it.map { it ->
//                        TeamEntity(it)
//                    }
//                }
//            teamDao.insertAllTeams(it)
//        }
//    }
//
//    fun getAllTeams():Single<List<Team>> {
//        if(hasConnection){
//            Single.just(1).subscribe{
//                it -> teamService.getAllTeams()
//            }
//        }
//    }
//
//
//    private fun saveToDatabase(team:List<TeamEntity>): Completable {
//       return Completable.create {
//           cornerDatabase.runInTransaction {teamDao.insertAllTeams(team)}
//       }
//    }
//
//
//
//    fun insertTeam(team: TeamEntity) = teamDao.insertTeam(team)
//
//    fun insertAllTeams(teamList: List<TeamEntity>) = teamDao.insertAllTeams(teamList)
//
//    fun getTeamByName(club: String): Single<TeamEntity> = teamDao.getTeamByName(club)
}


