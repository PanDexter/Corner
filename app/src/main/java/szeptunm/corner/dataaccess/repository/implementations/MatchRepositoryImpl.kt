package szeptunm.corner.dataaccess.repository.implementations

import io.reactivex.Single
import szeptunm.corner.dataaccess.database.dao.MatchDao
import szeptunm.corner.dataaccess.database.entity.MatchEntity
import javax.inject.Inject

class MatchRepositoryImpl @Inject constructor(var matchDao: MatchDao) {

     fun insertMatch(matchEntity: MatchEntity) = matchDao.insertMatch(matchEntity)

     fun insertAllMatches(matchEntityList: List<MatchEntity>) = matchDao.insertAllMatches(matchEntityList)

     fun getAllMatches(): Single<List<MatchEntity>> = matchDao.getAllMatches()

     fun getMatchByTeamId(teamId: Int): Single<List<MatchEntity>> = matchDao.getMatchByTeamId(teamId)
}