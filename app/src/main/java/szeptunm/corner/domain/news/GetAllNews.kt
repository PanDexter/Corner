package szeptunm.corner.domain.news

import io.reactivex.Single
import szeptunm.corner.dataaccess.api.model.NewsResponse
import szeptunm.corner.dataaccess.repository.implementations.NewsRepository
import szeptunm.corner.entity.News
import javax.inject.Inject
import javax.inject.Singleton

class GetAllNews @Inject constructor(val newsRepository: NewsRepository) {

    fun execute(): Single<List<News>>{
        return newsRepository.getAllNews()
    }
}