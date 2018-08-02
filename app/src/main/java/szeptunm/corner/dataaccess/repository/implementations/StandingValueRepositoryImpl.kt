package szeptunm.corner.dataaccess.repository.implementations

import io.reactivex.Single
import szeptunm.corner.dataaccess.database.dao.StandingValueDao
import szeptunm.corner.dataaccess.database.entity.StandingValue
import szeptunm.corner.dataaccess.repository.interfaces.StandingValueRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StandingValueRepositoryImpl @Inject constructor(var standingValueDao: StandingValueDao) :
        StandingValueRepository {

    override fun insertStandingValue(standingValue: StandingValue) =
            standingValueDao.insertStandingValue(standingValue)

    override fun insertAllStandingValues(
            standingValueList: List<StandingValue>) = standingValueDao.insertAllStandingValues(standingValueList)

    override fun getAllStandingValue(): Single<List<StandingValue>> = standingValueDao.getAllStandingValues()

    override fun getStandingValueById(id: Int): Single<StandingValue> = standingValueDao.getStandingValueById(id)

    override fun getStandingValueByCompetitionId(competitionId: Int): Single<List<StandingValue>> =
            standingValueDao.getStandingValueByCompetitionId(competitionId)
}