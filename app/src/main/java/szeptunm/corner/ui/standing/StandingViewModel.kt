package szeptunm.corner.ui.standing

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import szeptunm.corner.domain.standings.GetAllStandings
import szeptunm.corner.entity.Standing
import javax.inject.Inject

class StandingViewModel @Inject constructor(private var getAllStandings: GetAllStandings) {

    private var subject: BehaviorSubject<List<StandingItem>> = BehaviorSubject.create()
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()
    private var items: List<StandingItem> = emptyList()

    fun observeStandings(): Observable<List<StandingItem>> = subject

    init {
        getAllStandings.execute()
                .subscribeOn(Schedulers.computation())
                .map { standings -> standings.map { convertIntoItems(it) } }
                .doOnNext {
                    this.items = it
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { items -> subject.onNext(items) }
                .addTo(compositeDisposable)
    }

    private fun convertIntoItems(standing: Standing): StandingItem = StandingItem(standing)
}