package szeptunm.corner.dataaccess.api.service

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import szeptunm.corner.dataaccess.api.model.TeamResponse

interface TeamService{

    @GET("/v2/teams/{id}")
    fun getTeamById(@Query("id") id:Int): Single<TeamResponse>

    @GET("/v2/teams")
    fun getAllTeams():Single<List<TeamResponse>>
}