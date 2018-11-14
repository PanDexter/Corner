package szeptunm.corner.domain.splashScreen

import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import szeptunm.corner.domain.news.GetTeamNews
import szeptunm.corner.domain.players.GetTeamPlayers
import szeptunm.corner.domain.schedule.GetTeamMatches
import szeptunm.corner.domain.standings.GetAllStandings
import szeptunm.corner.entity.ClubInfo
import javax.inject.Inject

class InitializeData @Inject constructor() {

    @Inject
    lateinit var getTeamPlayers: GetTeamPlayers
    @Inject
    lateinit var getTeamNews: GetTeamNews
    @Inject
    lateinit var getTeamMatches: GetTeamMatches
    @Inject
    lateinit var getAllStandings: GetAllStandings

    fun execute(clubInfo: ClubInfo): Completable =
            Completable.fromAction {
                getTeamMatches.execute(clubInfo)
                        .observeOn(Schedulers.io())
                        .subscribeOn(Schedulers.io())
                        .subscribe {
                            getAllStandings.execute(clubInfo)
                                    .observeOn(Schedulers.computation())
                                    .subscribeOn(Schedulers.computation())
                                    .subscribe {
                                        getTeamNews.execute(clubInfo)
                                                .observeOn(Schedulers.io())
                                                .subscribeOn(Schedulers.io())
                                                .subscribe {
                                                    getTeamPlayers.execute(clubInfo)
                                                            .observeOn(Schedulers.computation())
                                                            .subscribeOn(Schedulers.computation())
                                                }
                                    }
                        }
            }
}