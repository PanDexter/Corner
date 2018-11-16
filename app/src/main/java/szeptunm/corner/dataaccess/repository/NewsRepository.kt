package szeptunm.corner.dataaccess.repository

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.SingleTransformer
import io.reactivex.schedulers.Schedulers
import szeptunm.corner.BuildConfig
import szeptunm.corner.dataaccess.api.model.NewsResponse
import szeptunm.corner.dataaccess.api.pojo.Item
import szeptunm.corner.dataaccess.api.service.NewsService
import szeptunm.corner.dataaccess.database.DatabaseTransaction
import szeptunm.corner.dataaccess.database.dao.NewsDao
import szeptunm.corner.dataaccess.database.entity.NewsEntity
import szeptunm.corner.entity.ClubInfo
import szeptunm.corner.entity.News
import timber.log.Timber
import javax.inject.Inject

class NewsRepository @Inject constructor(private var newsDao: NewsDao, private var newsService: NewsService,
        private val databaseTransaction: DatabaseTransaction) {

    private val newsTransformer: SingleTransformer<List<NewsEntity>, List<News>> =
            SingleTransformer { upstream ->
                upstream.flattenAsObservable { list -> list }
                        .map { News(it) }
                        .toList()
            }

    fun getAllNews(clubInfo: ClubInfo): Observable<List<News>> = getNewsFromDb(clubInfo)

    fun getNewsFromDb(clubInfo: ClubInfo): Observable<List<News>> {
        return newsDao.getNewsByTeamId(clubInfo.matchTeamId)
                .compose(newsTransformer)
                .filter { it.isNotEmpty() }
                .toObservable()
                .doOnNext {
                    Timber.d("Dispatching ${it.size} news from DB...")
                }
    }

    fun getNewsFromApi(clubInfo: ClubInfo): Completable {
        return newsService.getAllNews(clubInfo.newsUrl,
                BuildConfig.NEWS_KEY)
                .observeOn(Schedulers.computation())
                .retry(3)
                .flatMapCompletable {
                    mapResponseToEntity(it, clubInfo)
                }
    }

    private fun mapResponseToEntity(newsResponse: NewsResponse, clubInfo: ClubInfo): Completable {
        val newsList: MutableList<NewsEntity> = ArrayList()
        for (i in 0 until newsResponse.items.size) {
            newsResponse.items[i].let {
                newsList.add(
                        NewsEntity(it.title, changeDescription(it), it.pubDate, it.enclosure.imageURL, it.link,
                                clubInfo.matchTeamId))
            }
        }
        return saveToDatabase(newsList)
    }

    private fun saveToDatabase(newsList: List<NewsEntity>): Completable =
            databaseTransaction.runTransaction { newsDao.insertAllNews(newsList) }.subscribeOn(Schedulers.io())

    private fun changeDescription(item: Item): String {
        if (item.enclosure.imageURL != null) {
            val first = item.description.split('>')
            return first[1]
        }
        return ""
    }
}

