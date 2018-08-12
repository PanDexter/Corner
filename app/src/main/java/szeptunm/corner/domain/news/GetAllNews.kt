package szeptunm.corner.domain.news

import io.reactivex.Completable
import io.reactivex.Single
import szeptunm.corner.dataaccess.repository.implementations.NewsRepository
import szeptunm.corner.entity.News
import javax.inject.Inject

class GetAllNews @Inject constructor() {


    @Inject
    lateinit var newsRepository: NewsRepository


    fun execute(): Single<List<News>>{
        return newsRepository.getAllNews()
    }

    fun executeFromApi(): Completable {
        return newsRepository.updateNewsDatabase()
    }
}