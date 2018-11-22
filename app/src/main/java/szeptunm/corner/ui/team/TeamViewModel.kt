package szeptunm.corner.ui.team

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import szeptunm.corner.domain.players.GetTeamPlayers
import szeptunm.corner.entity.ClubInfo
import szeptunm.corner.entity.Player
import timber.log.Timber
import javax.inject.Inject

class TeamViewModel @Inject constructor(getTeamPlayers: GetTeamPlayers, clubInfo: ClubInfo) {

    private var subject: BehaviorSubject<List<PlayerItem>> = BehaviorSubject.create()
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()
    private var items: List<PlayerItem> = emptyList()

    fun observePlayers(): Observable<List<PlayerItem>> = subject

    init {
        getTeamPlayers.execute(clubInfo)
                .subscribeOn(Schedulers.computation())
                .map { player -> player.map { convertIntoItems(it) } }
                .doOnNext {
                    this.items = it
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subject::onNext) { Timber.e(it, "Something went wrong during team initialization") }
                .addTo(compositeDisposable)
    }

    private fun convertIntoItems(player: Player): PlayerItem {
        return PlayerItem(player)
    }
}