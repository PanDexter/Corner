package szeptunm.corner.domain.competitions

import io.reactivex.Single
import szeptunm.corner.dataaccess.repository.MatchRepository
import szeptunm.corner.entity.Competition
import javax.inject.Inject

class GetCompetitionById @Inject constructor(private val matchRepository: MatchRepository) {

    fun execute(id: Int): Single<Competition> =
            matchRepository.getCompetitionById(id)
}