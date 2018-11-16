package szeptunm.corner.domain.news

import io.reactivex.Completable
import szeptunm.corner.dataaccess.repository.NewsRepository
import szeptunm.corner.entity.ClubInfo
import javax.inject.Inject

class GetNewsFromApi @Inject constructor(private val newsRepository: NewsRepository) {

    fun execute(clubInfo: ClubInfo): Completable = newsRepository.getNewsFromApi(clubInfo)
}