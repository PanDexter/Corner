package szeptunm.corner.dataaccess.repository

import android.annotation.SuppressLint
import io.reactivex.Observable
import io.reactivex.SingleTransformer
import io.reactivex.schedulers.Schedulers
import szeptunm.corner.BuildConfig
import szeptunm.corner.dataaccess.api.model.NewsResponse
import szeptunm.corner.dataaccess.api.pojo.Item
import szeptunm.corner.dataaccess.api.service.NewsService
import szeptunm.corner.dataaccess.database.dao.NewsDao
import szeptunm.corner.dataaccess.database.entity.NewsEntity
import szeptunm.corner.entity.News
import timber.log.Timber
import javax.inject.Inject

class NewsRepository @Inject constructor(private var newsDao: NewsDao, private var newsService: NewsService) {

    private val newsTransformer: SingleTransformer<List<NewsEntity>, List<News>> =
            SingleTransformer { upstream ->
            upstream.flattenAsObservable { list -> list }
                    .map { News(it) }
                    .toList()
        }

    fun getAllNews(): Observable<List<News>> {
        return Observable.concatArray(
                getNewsFromDb(), getNewsFromApi()
        )
    }

    private fun getNewsFromDb(): Observable<List<News>> {
        return newsDao.getAllNews()
                .compose(newsTransformer)
                .filter { it.isNotEmpty() }
                .toObservable()
                .doOnNext {
                    Timber.d("Dispatching ${it.size} news from DB...")
                }
    }

    private fun getNewsFromApi(): Observable<List<News>> {
        return newsService.getAllNews("http://www.espnfc.com/club/barcelona/83/rss",
                BuildConfig.NEWS_KEY)
                .map {
                    mapResponseToEntity(it)
                }
                .doOnSuccess {
                    saveToDatabase(it)
                }
                .compose(newsTransformer)
                .toObservable()
    }


    private fun mapResponseToEntity(newsResponse: NewsResponse): List<NewsEntity> {
        val newsList: MutableList<NewsEntity> = ArrayList()
        for (i in 0 until newsResponse.items.size) {
            newsResponse.items[i].let {
                newsList.add(
                        NewsEntity(0, it.title, changeDescription(it), it.pubDate, it.enclosure.imageURL, it.link,
                                null))
            }
        }
        return newsList
    }

    @SuppressLint("CheckResult")
    private fun saveToDatabase(newsList: List<NewsEntity>) {
        Observable.fromCallable { newsDao.insertAllNews(newsList) }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe {
                    Timber.d("Insert ${newsList.size} news to DB...")
                }
    }

    private fun changeDescription(item: Item): String {
        if (item.enclosure.imageURL != null) {
            val first = item.description.split('>')
            return first[1]
        }
        return ""
    }
}

