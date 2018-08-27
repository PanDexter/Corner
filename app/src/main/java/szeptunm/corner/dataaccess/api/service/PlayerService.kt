package szeptunm.corner.dataaccess.api.service

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import szeptunm.corner.dataaccess.api.model.PlayerResponse

interface PlayerService {

    @GET("/lookup_all_players.php?")
    fun getAllPlayers(@Query("id") id: Int): Single<PlayerResponse>
}