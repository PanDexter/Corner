package szeptunm.corner.dataaccess.api.service

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import szeptunm.corner.dataaccess.api.model.StandingResponse

interface StandingService {

    @GET("/v2/competitions/{id}/standings")
    fun getStandingById(@Header("X-Auth-Token") token: String, @Path("id") id: Int): Single<StandingResponse>
}