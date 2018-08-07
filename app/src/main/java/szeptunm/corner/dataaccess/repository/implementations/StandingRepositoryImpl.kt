package szeptunm.corner.dataaccess.repository.implementations

import io.reactivex.Single
import szeptunm.corner.dataaccess.database.dao.StandingDao
import szeptunm.corner.dataaccess.database.entity.StandingEntity
import javax.inject.Inject

class StandingRepositoryImpl @Inject constructor(var standingDao: StandingDao){

     fun insertStanding(standingEntity: StandingEntity) = standingDao.insertStanding(standingEntity)

     fun insertAllStandings(standingEntityList: List<StandingEntity>) = standingDao.insertAllStandings(standingEntityList)

     fun getAllStandings(): Single<List<StandingEntity>> = standingDao.getAllStandings()

     fun getStandingById(id: Int): Single<StandingEntity> = standingDao.getStandingById(id)

     fun getStandingByMatchDay(matchDay: Int): Single<StandingEntity> = standingDao.getStandingByMatchDay(matchDay)

     fun getStandingByMatchDayAndId(matchDay: Int,
            id: Int): Single<StandingEntity> = standingDao.getStandingByMatchDayAndId(matchDay, id)
}