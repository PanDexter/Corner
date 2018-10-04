package szeptunm.corner.domain.splashScreen

import szeptunm.corner.dataaccess.repository.ClubInfoRepository
import javax.inject.Inject

class InitializeClubInfo @Inject constructor() {

    @Inject
    lateinit var repository: ClubInfoRepository

    fun execute() = repository.saveClubInfoToDatabase()
}