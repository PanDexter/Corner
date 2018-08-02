package szeptunm.corner.dataaccess.repository

import io.reactivex.Single
import szeptunm.corner.dataaccess.database.dao.StandingDao
import szeptunm.corner.dataaccess.database.entity.Standing
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StandingRepositoryImpl @Inject constructor(var standingDao: StandingDao) : StandingRepository {

    override fun insertStanding(standing: Standing) = standingDao.insertStanding(standing)

    override fun getAllStandings(): Single<List<Standing>> = standingDao.getAllStandings()

    override fun getStandingById(id: Int): Single<Standing> = standingDao.getStandingById(id)

    override fun getStandingByMatchDay(matchDay: Int): Single<Standing> = standingDao.getStandingByMatchDay(matchDay)
}