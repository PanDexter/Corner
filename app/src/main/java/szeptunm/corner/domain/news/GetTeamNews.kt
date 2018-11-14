package szeptunm.corner.domain.news

import io.reactivex.Observable
import szeptunm.corner.dataaccess.repository.NewsRepository
import szeptunm.corner.entity.ClubInfo
import szeptunm.corner.entity.News
import javax.inject.Inject

class GetTeamNews @Inject constructor(private val newsRepository: NewsRepository) {

    fun execute(clubInfo: ClubInfo): Observable<List<News>> = newsRepository.getAllNews(clubInfo)
}