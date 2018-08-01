package szeptunm.corner.database.repository

import io.reactivex.Single
import szeptunm.corner.database.dao.TeamDao
import szeptunm.corner.database.entity.Team
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TeamRepositoryImpl @Inject constructor(var teamDao: TeamDao):TeamRepository{

    override fun insertTeam(team: Team) = teamDao.insertTeam(team)

    override fun insertAllTeams(teamList: List<Team>) = teamDao.insertAllTeams(teamList)

    override fun getAllTeams(): Single<List<Team>> = teamDao.getAllTeams()

    override fun getTeamByName(name: String): Single<Team> = teamDao.getTeamByName(name)
}