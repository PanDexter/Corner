package szeptunm.corner.dataaccess.repository.implementations

import io.reactivex.Single
import szeptunm.corner.dataaccess.database.dao.TeamDao
import szeptunm.corner.dataaccess.database.entity.Team
import szeptunm.corner.dataaccess.repository.interfaces.TeamRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TeamRepositoryImpl @Inject constructor(var teamDao: TeamDao) :
        TeamRepository {

    override fun getTeamById(id: Int): Single<Team> = teamDao.getTeamById(id)

    override fun insertTeam(team: Team) = teamDao.insertTeam(team)

    override fun insertAllTeams(teamList: List<Team>) = teamDao.insertAllTeams(teamList)

    override fun getAllTeams(): Single<List<Team>> = teamDao.getAllTeams()

    override fun getTeamByName(club: String): Single<Team> = teamDao.getTeamByName(club)
}