package szeptunm.corner.domain.news

import szeptunm.corner.dataaccess.repository.NewsRepository
import szeptunm.corner.entity.ClubInfo
import javax.inject.Inject

class GetNewsFromApi @Inject constructor() {

    @Inject
    lateinit var newsRepository: NewsRepository

    fun execute(clubInfo: ClubInfo) = newsRepository.getNewsFromApi(clubInfo)
}