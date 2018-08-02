package szeptunm.corner.dataaccess.repository.interfaces

import io.reactivex.Single
import szeptunm.corner.dataaccess.database.entity.Team

interface TeamRepository {

    fun insertTeam(team: Team)
    fun insertAllTeams(teamList: List<Team>)
    fun getAllTeams(): Single<List<Team>>
    fun getTeamById(id: Int): Single<Team>
    fun getTeamByName(club: String): Single<Team>
}