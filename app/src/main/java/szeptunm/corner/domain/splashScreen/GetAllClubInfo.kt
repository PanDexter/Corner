package szeptunm.corner.domain.splashScreen

import io.reactivex.Single
import szeptunm.corner.dataaccess.repository.ClubInfoRepository
import szeptunm.corner.entity.ClubInfo
import javax.inject.Inject

class GetAllClubInfo @Inject constructor() {

    @Inject
    lateinit var repository: ClubInfoRepository

    fun execute(): Single<List<ClubInfo>> = repository.getClubInfoFromDb()
}