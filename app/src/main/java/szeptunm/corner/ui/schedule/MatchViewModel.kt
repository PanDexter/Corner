package szeptunm.corner.ui.schedule

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import szeptunm.corner.domain.competitions.GetCompetitionById
import szeptunm.corner.domain.schedule.GetAllMatches
import szeptunm.corner.domain.teams.GetTeamById
import szeptunm.corner.entity.Competition
import szeptunm.corner.entity.Match
import szeptunm.corner.entity.MatchSchedule
import szeptunm.corner.entity.Team
import timber.log.Timber
import javax.inject.Inject

class MatchViewModel @Inject constructor(getAllMatches: GetAllMatches, private val getTeamById: GetTeamById,
        private val getCompetitionById: GetCompetitionById) {

    private var subject: BehaviorSubject<List<MatchItem>> = BehaviorSubject.create()
    private var homeTeamSubject: BehaviorSubject<Team> = BehaviorSubject.create()
    private var awayTeamSubject: BehaviorSubject<Team> = BehaviorSubject.create()
    private var competitionSubject: BehaviorSubject<Competition> = BehaviorSubject.create()
    private lateinit var homeTeam: Team
    private lateinit var awayTeam: Team
    private lateinit var competition: Competition
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()
    private var items: List<MatchItem> = emptyList()

    fun observeMatches(): Observable<List<MatchItem>> = subject

    init {
        getAllMatches.execute()
                .subscribeOn(Schedulers.computation())
                .map { match ->
                    match.map {
                        getTeamById.execute(it.homeTeam).doOnSuccess {
                            homeTeamSubject.onNext(it)
                        }
                        getTeamById.execute(it.awayTeam).doOnSuccess {
                            awayTeamSubject.onNext(it)
                        }
                        getCompetitionById.execute(it.competition).doOnSuccess {
                            competitionSubject.onNext(it)
                        }
                        observeHomeTeam()
                        observeAwayTeam()
                        observeCompetition()

                        convertIntoItems(it, homeTeam.name, awayTeam.name,
                                competition.name)
                    }
                }
                .doOnNext {
                    this.items = it
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ items ->
                    subject.onNext(items)
                }, { t -> Timber.e(t) })
                .addTo(compositeDisposable)
    }

    private fun convertIntoItems(match: Match, homeTeam: String, awayTeam: String,
            competitionName: String): MatchItem {
        return MatchItem(MatchSchedule(
                homeTeamGoalFull = match.homeTeamGoalFull,
                awayTeamGoalFull = match.awayTeamGoalFull,
                homeTeamGoalExtra = match.homeTeamGoalExtra,
                awayTeamGoalExtra = match.awayTeamGoalExtra,
                homePenalties = match.homePenalties,
                awayPenalties = match.awayPenalties,
                homeTeam = homeTeam,
                awayTeam = awayTeam,
                date = match.date,
                competition = competitionName
        ))
    }

    private fun observeHomeTeam() {
        homeTeamSubject.subscribe {
            homeTeam = it
        }.addTo(compositeDisposable)
    }

    private fun observeAwayTeam() {
        awayTeamSubject.subscribe {
            awayTeam = it
        }.addTo(compositeDisposable)
    }

    private fun observeCompetition() {
        competitionSubject.subscribe {
            competition = it
        }.addTo(compositeDisposable)
    }
}