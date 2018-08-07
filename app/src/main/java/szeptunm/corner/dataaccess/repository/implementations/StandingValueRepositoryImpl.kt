package szeptunm.corner.dataaccess.repository.implementations

import io.reactivex.Single
import szeptunm.corner.dataaccess.database.dao.StandingValueDao
import szeptunm.corner.dataaccess.database.entity.StandingValueEntity
import javax.inject.Inject

class StandingValueRepositoryImpl @Inject constructor(var standingValueDao: StandingValueDao){

     fun insertStandingValue(standingValueEntity: StandingValueEntity) =
            standingValueDao.insertStandingValue(standingValueEntity)

     fun insertAllStandingValues(
            standingValueEntityList: List<StandingValueEntity>) = standingValueDao.insertAllStandingValues(standingValueEntityList)

     fun getAllStandingValue(): Single<List<StandingValueEntity>> = standingValueDao.getAllStandingValues()

     fun getStandingValueById(id: Int): Single<StandingValueEntity> = standingValueDao.getStandingValueById(id)

     fun getStandingValueByCompetitionId(competitionId: Int): Single<List<StandingValueEntity>> =
            standingValueDao.getStandingValueByCompetitionId(competitionId)
}