package szeptunm.corner.ui.news

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import szeptunm.corner.domain.news.GetAllNews
import szeptunm.corner.entity.ClubInfo
import szeptunm.corner.entity.News
import timber.log.Timber
import javax.inject.Inject

class NewsViewModel @Inject constructor(private var getAllNews: GetAllNews, clubInfo: ClubInfo) {

    private var subject:BehaviorSubject<List<NewsItem>> = BehaviorSubject.create()
    private var compositeDisposable:CompositeDisposable = CompositeDisposable()
    private var items:List<NewsItem> = emptyList()

    fun observeNews(): Observable<List<NewsItem>> = subject

    init {
        getAllNews.execute(clubInfo)
                .subscribeOn(Schedulers.computation())
                .map { news -> news.map { convertIntoItems(it) } }
                .doOnNext {
                    this.items = it
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subject::onNext) { Timber.e(it, "Something went wrong during news initialization") }
                .addTo(compositeDisposable)
    }

    private fun convertIntoItems(news: News): NewsItem {
        return NewsItem(news)
    }
}