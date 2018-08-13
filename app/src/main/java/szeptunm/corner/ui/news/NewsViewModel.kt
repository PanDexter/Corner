package szeptunm.corner.ui.news

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import szeptunm.corner.domain.news.GetAllNews
import szeptunm.corner.entity.News
import javax.inject.Inject

class NewsViewModel @Inject constructor(var getAllNews: GetAllNews) {

    private var subject:BehaviorSubject<List<NewsItem>> = BehaviorSubject.create()
    private var compositeDisposable:CompositeDisposable = CompositeDisposable()
    private var items:List<NewsItem> = emptyList()

    fun observeNews(): Observable<List<NewsItem>> = subject

    init {
        getAllNews.executeFromApi()
                .observeOn(Schedulers.computation())
                .subscribeOn(Schedulers.computation())
                .subscribe {
                    getNewsFromDatabase()
                }
                .addTo(compositeDisposable)
    }

    private fun getNewsFromDatabase() {
        getAllNews.execute()
                .subscribeOn(Schedulers.computation())
                .map { news -> news.map { convertIntoItems(it) } }
                .doOnSuccess { this.items = it }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { items -> subject.onNext(items) }
                .addTo(compositeDisposable)
    }

    private fun convertIntoItems(news: News): NewsItem {
        return NewsItem(news)
    }
}