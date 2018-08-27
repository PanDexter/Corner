package szeptunm.corner.ui.team

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import szeptunm.corner.domain.players.GetAllPlayers
import szeptunm.corner.entity.Player
import javax.inject.Inject

class TeamViewModel @Inject constructor(private var getAllPlayers: GetAllPlayers) {

    private var subject: BehaviorSubject<List<PlayerItem>> = BehaviorSubject.create()
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()
    private var items: List<PlayerItem> = emptyList()

    fun observePlayers(): Observable<List<PlayerItem>> = subject

    init {
        getAllPlayers.execute()
                .subscribeOn(Schedulers.computation())
                .map { player -> player.map { convertIntoItems(it) } }
                .doOnNext {
                    this.items = it
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { items -> subject.onNext(items) }
                .addTo(compositeDisposable)
    }

    private fun convertIntoItems(player: Player): PlayerItem {
        return PlayerItem(player)
    }
}