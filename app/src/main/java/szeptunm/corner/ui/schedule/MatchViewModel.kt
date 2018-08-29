package szeptunm.corner.ui.schedule

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import szeptunm.corner.domain.schedule.GetAllMatches
import szeptunm.corner.entity.Match
import javax.inject.Inject

class MatchViewModel @Inject constructor(private var getAllMatches: GetAllMatches) {

    private var subject: BehaviorSubject<List<MatchItem>> = BehaviorSubject.create()
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()
    private var items: List<MatchItem> = emptyList()

    fun observeMatches(): Observable<List<MatchItem>> = subject

    init {
        getAllMatches.execute()
                .subscribeOn(Schedulers.computation())
                .map { match -> match.map { convertIntoItems(it) } }
                .doOnNext {
                    this.items = it
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { items -> subject.onNext(items) }
                .addTo(compositeDisposable)
    }

    private fun convertIntoItems(match: Match): MatchItem {
        return MatchItem(match)
    }
}