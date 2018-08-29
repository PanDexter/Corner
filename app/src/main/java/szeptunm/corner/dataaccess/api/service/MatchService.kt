package szeptunm.corner.dataaccess.api.service

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import szeptunm.corner.dataaccess.api.model.MatchResponse

interface MatchService {

    @GET("v2/teams/{id}/matches")
    fun getAllMatches(@Query("id") id: Int): Single<MatchResponse>
}