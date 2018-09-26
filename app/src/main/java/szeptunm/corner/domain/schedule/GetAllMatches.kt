package szeptunm.corner.domain.schedule

import io.reactivex.Observable
import io.reactivex.Single
import szeptunm.corner.dataaccess.repository.MatchRepository
import szeptunm.corner.entity.Match
import javax.inject.Inject

class GetAllMatches @Inject constructor() {

    @Inject
    lateinit var repository: MatchRepository

    fun execute(): Observable<List<Match>> {
        return repository.getAllMatches()
    }
}