package szeptunm.corner.domain.splashScreen

import szeptunm.corner.dataaccess.repository.ClubInfoRepository
import javax.inject.Inject

class GetClubInfoByName @Inject constructor() {

    @Inject
    lateinit var repository: ClubInfoRepository

    fun execute(club: String) = repository.getClubInfoByName(club)
}