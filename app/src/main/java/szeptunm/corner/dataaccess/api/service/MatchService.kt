package szeptunm.corner.dataaccess.api.service

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import szeptunm.corner.dataaccess.api.model.MatchResponse

interface MatchService {

    @GET("/v2/teams/{id}/matches")
    fun getAllMatches(@Header("X-Auth-Token") token: String, @Path("id") id: Int): Single<MatchResponse>
}