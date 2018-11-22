package szeptunm.corner.domain.schedule

import io.reactivex.Completable
import szeptunm.corner.dataaccess.repository.MatchRepository
import szeptunm.corner.entity.ClubInfo
import javax.inject.Inject

class GetMatchesFromApi @Inject constructor(val repository: MatchRepository) {

    fun execute(clubInfo: ClubInfo): Completable = repository.getMatchesFromApi(clubInfo)
}