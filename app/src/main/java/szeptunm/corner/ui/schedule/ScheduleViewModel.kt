package szeptunm.corner.ui.schedule

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.Singles
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import org.joda.time.DateTime
import szeptunm.corner.domain.competitions.GetCompetitionById
import szeptunm.corner.domain.schedule.GetAllMatches
import szeptunm.corner.domain.teams.GetTeamById
import szeptunm.corner.entity.ClubInfo
import szeptunm.corner.entity.Competition
import szeptunm.corner.entity.Match
import szeptunm.corner.entity.MatchSchedule
import szeptunm.corner.entity.Team
import szeptunm.corner.ui.schedule.MatchItem.Companion.MATCH_FUTURE
import szeptunm.corner.ui.schedule.MatchItem.Companion.MATCH_PAST
import timber.log.Timber
import javax.inject.Inject

class ScheduleViewModel @Inject constructor(getAllMatches: GetAllMatches,
        clubInfo: ClubInfo,
        private val getTeamById: GetTeamById,
        private val getCompetitionById: GetCompetitionById) {

    private var subject: BehaviorSubject<List<MatchItem>> = BehaviorSubject.create()
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()
    fun observeMatches(): Observable<List<MatchItem>> = subject

    init {
        getAllMatches.execute(clubInfo)
                .subscribeOn(Schedulers.computation())
                .flatMapSingle {
                    Observable.fromIterable(it)
                            .flatMapSingle { match ->
                                Singles.zip(getTeamById.execute(match.homeTeam)
                                        .subscribeOn(Schedulers.computation()),
                                        getTeamById.execute(match.awayTeam)
                                                .subscribeOn(Schedulers.computation()),
                                        getCompetitionById.execute(match.competition)
                                                .subscribeOn(Schedulers.computation())) { home, away, competition ->
                                    convertIntoItem(match, home, away, competition)
                                }
                            }
                            .toList()
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    it.sortBy {
                        DateTime(it.match.date)
                    }
                    subject.onNext(it)
                }) { Timber.e(it, "Something went wrong during fetching matches") }
                .addTo(compositeDisposable)
    }

    private fun convertIntoItem(match: Match, homeTeam: Team, awayTeam: Team,
            competitionName: Competition): MatchItem {
        if (match.homeTeamGoalFull == null) {
            return MatchItem(MATCH_FUTURE, MatchSchedule(
                    homeTeamGoalFull = match.homeTeamGoalFull,
                    awayTeamGoalFull = match.awayTeamGoalFull,
                    homeTeamGoalExtra = match.homeTeamGoalExtra,
                    awayTeamGoalExtra = match.awayTeamGoalExtra,
                    homePenalties = match.homePenalties,
                    awayPenalties = match.awayPenalties,
                    homeTeam = homeTeam.name,
                    awayTeam = awayTeam.name,
                    homeTeamId = match.homeTeam,
                    awayTeamId = match.awayTeam,
                    date = match.date,
                    competition = competitionName.name))
        } else {
            return MatchItem(MATCH_PAST, MatchSchedule(
                    homeTeamGoalFull = match.homeTeamGoalFull,
                    awayTeamGoalFull = match.awayTeamGoalFull,
                    homeTeamGoalExtra = match.homeTeamGoalExtra,
                    awayTeamGoalExtra = match.awayTeamGoalExtra,
                    homePenalties = match.homePenalties,
                    awayPenalties = match.awayPenalties,
                    homeTeam = homeTeam.name,
                    awayTeam = awayTeam.name,
                    homeTeamId = match.homeTeam,
                    awayTeamId = match.awayTeam,
                    date = match.date,
                    competition = competitionName.name
            ))
        }
    }
}