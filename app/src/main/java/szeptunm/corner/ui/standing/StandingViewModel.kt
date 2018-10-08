package szeptunm.corner.ui.standing

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.Singles
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import szeptunm.corner.domain.competitions.GetCompetitionById
import szeptunm.corner.domain.standings.GetAllStandings
import szeptunm.corner.domain.teams.GetTeamById
import szeptunm.corner.entity.ClubInfo
import szeptunm.corner.entity.Competition
import szeptunm.corner.entity.Standing
import szeptunm.corner.entity.Team
import timber.log.Timber
import javax.inject.Inject

class StandingViewModel @Inject constructor(private val getAllStandings: GetAllStandings,
        clubInfo: ClubInfo,
        private val getTeamById: GetTeamById,
        private val getCompetitionById: GetCompetitionById) {

    private var subject: BehaviorSubject<List<StandingItem>> = BehaviorSubject.create()
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun observeStandings(): Observable<List<StandingItem>> = subject

    init {
        getAllStandings.execute(clubInfo)
                .subscribeOn(Schedulers.computation())
                .flatMapSingle {
                    Observable.fromIterable(it)
                            .flatMapSingle { standing ->
                                Singles.zip(getTeamById.execute(standing.teamId)
                                        .subscribeOn(Schedulers.computation()),
                                        getCompetitionById.execute(standing.competitionId)
                                                .subscribeOn(Schedulers.computation())) { team, competition ->
                                    convertIntoItem(standing, team, competition)
                                }
                            }
                            .toSortedList { first, second -> first.table.position.compareTo(second.table.position) }
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subject::onNext) { Timber.e(it, "Something went wrong during standing initialization") }
                .addTo(compositeDisposable)
    }

    private fun convertIntoItem(standing: Standing, team: Team,
            competition: Competition): StandingItem = StandingItem(
            standing, team, competition)

}