package szeptunm.corner.domain.schedule

import io.reactivex.Observable
import szeptunm.corner.dataaccess.repository.MatchRepository
import szeptunm.corner.entity.ClubInfo
import szeptunm.corner.entity.Match
import javax.inject.Inject

class GetTeamMatches @Inject constructor(val repository: MatchRepository) {

    fun execute(clubInfo: ClubInfo): Observable<List<Match>> = repository.getAllMatches(clubInfo)
}