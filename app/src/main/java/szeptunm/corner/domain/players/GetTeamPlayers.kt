package szeptunm.corner.domain.players

import io.reactivex.Observable
import szeptunm.corner.dataaccess.repository.PlayerRepository
import szeptunm.corner.entity.ClubInfo
import szeptunm.corner.entity.Player
import javax.inject.Inject

class GetTeamPlayers @Inject constructor(val repository: PlayerRepository) {

    fun execute(club: ClubInfo): Observable<List<Player>> = repository.getAllPlayers(club)
}