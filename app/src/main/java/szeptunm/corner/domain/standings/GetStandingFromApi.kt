package szeptunm.corner.domain.standings

import io.reactivex.Completable
import szeptunm.corner.dataaccess.repository.StandingRepository
import szeptunm.corner.entity.ClubInfo
import javax.inject.Inject

class GetStandingFromApi @Inject constructor(val repository: StandingRepository) {

    fun execute(clubInfo: ClubInfo): Completable = repository.getStandingFromApi(clubInfo)
}