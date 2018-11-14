package szeptunm.corner.domain.splashScreen

import szeptunm.corner.dataaccess.repository.ClubInfoRepository
import javax.inject.Inject

class GetClubInfoByName @Inject constructor(val repository: ClubInfoRepository) {

    fun execute(club: String) = repository.getClubInfoByName(club)
}