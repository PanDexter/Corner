package szeptunm.corner.dataaccess.repository.interfaces

import io.reactivex.Single
import szeptunm.corner.dataaccess.database.entity.StandingValue

interface StandingValueRepository {

    fun insertStandingValue(standingValue: StandingValue)
    fun insertAllStandingValues(standingValueList: List<StandingValue>)
    fun getAllStandingValue(): Single<List<StandingValue>>
    fun getStandingValueById(id: Int): Single<StandingValue>
    fun getStandingValueByCompetitionId(competitionId: Int): Single<List<StandingValue>>
}