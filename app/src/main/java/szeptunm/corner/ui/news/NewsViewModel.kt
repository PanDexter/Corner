package szeptunm.corner.ui.news

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import szeptunm.corner.commons.Constants
import szeptunm.corner.commons.Preferences
import szeptunm.corner.domain.news.GetTeamNews
import szeptunm.corner.entity.ClubInfo
import szeptunm.corner.entity.News
import timber.log.Timber
import javax.inject.Inject

class NewsViewModel @Inject constructor(private val getTeamNews: GetTeamNews, val clubInfo: ClubInfo,
        val preferences: Preferences) {

    private var subject: BehaviorSubject<List<NewsItem>> = BehaviorSubject.create()
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()
    private var items: List<NewsItem> = emptyList()

    fun observeNews(): Observable<List<NewsItem>> = subject

    init {
        init()
    }

    fun init() {
        getTeamNews.execute(clubInfo)
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

    fun setNewClubName(clubName: String) {
        preferences.putPreferenceString(Constants.KEY_CLUB_NAME, clubName)
    }
}