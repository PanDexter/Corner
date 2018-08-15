package szeptunm.corner.domain.news

import io.reactivex.Observable
import szeptunm.corner.dataaccess.repository.implementations.NewsRepository
import szeptunm.corner.entity.News
import javax.inject.Inject

class GetAllNews @Inject constructor() {


    @Inject
    lateinit var newsRepository: NewsRepository

    fun execute(): Observable<List<News>> {
        return newsRepository.getAllNews()
    }

}