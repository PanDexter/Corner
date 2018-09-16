package szeptunm.corner.domain.teams

import io.reactivex.Single
import szeptunm.corner.dataaccess.repository.MatchRepository
import szeptunm.corner.entity.Team
import javax.inject.Inject

class GetTeamById @Inject constructor(private val matchRepository: MatchRepository) {


    fun execute(id: Int): Single<Team> =
            matchRepository.getTeamById(id)
}