package szeptunm.corner.domain.players

import io.reactivex.Observable
import szeptunm.corner.dataaccess.repository.PlayerRepository
import szeptunm.corner.entity.Player
import javax.inject.Inject

class GetAllPlayers @Inject constructor() {

    @Inject
    lateinit var repository: PlayerRepository

    fun execute(club: Int): Observable<List<Player>> {
        return repository.getAllPlayers(club)
    }
}