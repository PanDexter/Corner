package szeptunm.corner.domain.news

import szeptunm.corner.dataaccess.repository.NewsRepository
import szeptunm.corner.entity.ClubInfo
import javax.inject.Inject

class GetNewsFromDb @Inject constructor() {

    @Inject
    lateinit var newsRepository: NewsRepository

    fun execute(clubInfo: ClubInfo) = newsRepository.getNewsFromDb(clubInfo)
}