package szeptunm.corner.dataaccess.repository

import io.reactivex.Single
import szeptunm.corner.dataaccess.database.entity.Team

interface TeamRepository {

    fun insertTeam(team: Team)
    fun insertAllTeams(teamList: List<Team>)
    fun getAllTeams(): Single<List<Team>>
    fun getTeamByName(name: String): Single<Team>
}