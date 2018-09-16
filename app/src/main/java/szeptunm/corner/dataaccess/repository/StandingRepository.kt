package szeptunm.corner.dataaccess.repository

import io.reactivex.SingleTransformer
import szeptunm.corner.dataaccess.api.service.StandingService
import szeptunm.corner.dataaccess.database.dao.StandingDao
import szeptunm.corner.dataaccess.database.entity.StandingEntity
import szeptunm.corner.entity.Standing
import javax.inject.Inject

class StandingRepository @Inject constructor(
        private var standingDao: StandingDao, private var standingService: StandingService) {

    private val standingTransformer: SingleTransformer<List<StandingEntity>, List<Standing>> =
            SingleTransformer { upstream ->
                upstream.flattenAsObservable { list -> list }
                        .map { Standing(it) }
                        .toList()
            }
}