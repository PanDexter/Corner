package szeptunm.corner.dataaccess.api.service

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import szeptunm.corner.dataaccess.api.model.StandingResponse

interface StandingService {

    @GET("/v2/competitions/{id}/standings")
    fun getStandingById(@Query("id") id: Int):Single<StandingResponse>
}