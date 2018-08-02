package szeptunm.corner.dataaccess.repository.implementations

import io.reactivex.Single
import szeptunm.corner.dataaccess.database.dao.MatchDao
import szeptunm.corner.dataaccess.database.entity.Match
import szeptunm.corner.dataaccess.repository.interfaces.MatchRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MatchRepositoryImpl @Inject constructor(var matchDao: MatchDao) : MatchRepository {

    override fun insertMatch(match: Match) = matchDao.insertMatch(match)

    override fun insertAllMatches(matchList: List<Match>) = matchDao.insertAllMatches(matchList)

    override fun getAllMatches(): Single<List<Match>> = matchDao.getAllMatches()

    override fun getMatchById(id: Int): Single<Match> = matchDao.getMatchById(id)

    override fun getMatchByTeamId(teamId: Int): Single<List<Match>> = matchDao.getMatchByTeamId(teamId)
}