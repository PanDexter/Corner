package szeptunm.corner.domain.players

import io.reactivex.Completable
import szeptunm.corner.dataaccess.repository.PlayerRepository
import szeptunm.corner.entity.ClubInfo
import javax.inject.Inject

class GetTeamFromApi @Inject constructor(private val playerRepository: PlayerRepository) {

    fun execute(clubInfo: ClubInfo): Completable = playerRepository.getPlayersFromApi(clubInfo.teamApiId)
}