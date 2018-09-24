package szeptunm.corner.domain.standings

import io.reactivex.Observable
import szeptunm.corner.dataaccess.repository.StandingRepository
import szeptunm.corner.entity.Standing
import javax.inject.Inject

class GetAllStandings @Inject constructor() {

    @Inject
    lateinit var repository: StandingRepository

    fun execute(): Observable<List<Standing>> = repository.getAllStandings()
}