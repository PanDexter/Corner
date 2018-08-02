package szeptunm.corner.dataaccess.repository.interfaces

import io.reactivex.Single
import szeptunm.corner.dataaccess.database.entity.Match

interface MatchRepository {

    fun insertMatch(match: Match)
    fun insertAllMatches(matchList:List<Match>)
    fun getAllMatches(): Single<List<Match>>
    fun getMatchById(id:Int):Single<Match>
    fun getMatchByTeamId(teamId:Int):Single<List<Match>>
}