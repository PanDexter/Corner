package szeptunm.corner.domain.standings

import io.reactivex.Observable
import szeptunm.corner.dataaccess.repository.StandingRepository
import szeptunm.corner.entity.ClubInfo
import szeptunm.corner.entity.Standing
import javax.inject.Inject

class GetAllStandings @Inject constructor(val repository: StandingRepository) {

    fun execute(clubInfo: ClubInfo): Observable<List<Standing>> = repository.getAllStandings(clubInfo)
}