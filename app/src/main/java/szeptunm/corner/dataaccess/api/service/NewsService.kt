package szeptunm.corner.dataaccess.api.service

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import szeptunm.corner.dataaccess.api.model.NewsResponse
import szeptunm.corner.dataaccess.api.model.StandingResponse

interface NewsService {

    @GET("/api.json?")
    fun getAllNews(@Query("rss_url") url:String, @Query("api_key") key:String):Single<NewsResponse>

}