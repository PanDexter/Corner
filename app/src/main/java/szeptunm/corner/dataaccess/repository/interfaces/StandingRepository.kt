package szeptunm.corner.dataaccess.repository.interfaces

import io.reactivex.Single
import szeptunm.corner.dataaccess.database.entity.Standing

interface StandingRepository {

    fun insertStanding(standing: Standing)
    fun insertAllStandings(standingList:List<Standing>)
    fun getAllStandings(): Single<List<Standing>>
    fun getStandingById(id: Int): Single<Standing>
    fun getStandingByMatchDay(matchDay: Int): Single<Standing>
    fun getStandingByMatchDayAndId(matchDay: Int, id: Int): Single<Standing>
}