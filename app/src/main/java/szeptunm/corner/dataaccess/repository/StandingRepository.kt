package szeptunm.corner.dataaccess.repository

import io.reactivex.Single
import szeptunm.corner.dataaccess.database.entity.Standing

interface StandingRepository {

    fun insertStanding(standing: Standing)
    fun getAllStandings(): Single<List<Standing>>
    fun getStandingById(id: Int): Single<Standing>
    fun getStandingByMatchDay(matchDay: Int): Single<Standing>
}