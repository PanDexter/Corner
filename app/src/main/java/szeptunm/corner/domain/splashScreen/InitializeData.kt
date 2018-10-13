package szeptunm.corner.domain.splashScreen

import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import szeptunm.corner.domain.news.GetAllNews
import szeptunm.corner.domain.players.GetAllPlayers
import szeptunm.corner.domain.schedule.GetAllMatches
import szeptunm.corner.domain.standings.GetAllStandings
import szeptunm.corner.entity.ClubInfo
import javax.inject.Inject

class InitializeData @Inject constructor() {

    @Inject
    lateinit var getAllPlayers: GetAllPlayers
    @Inject
    lateinit var getAllNews: GetAllNews
    @Inject
    lateinit var getAllMatches: GetAllMatches
    @Inject
    lateinit var getAllStandings: GetAllStandings

    fun execute(clubInfo: ClubInfo): Completable =
            Completable.fromAction {
                getAllMatches.execute(clubInfo)
                        .observeOn(Schedulers.io())
                        .subscribeOn(Schedulers.io())
                        .subscribe {
                            getAllStandings.execute(clubInfo)
                                    .observeOn(Schedulers.computation())
                                    .subscribeOn(Schedulers.computation())
                                    .subscribe {
                                        getAllNews.execute(clubInfo)
                                                .observeOn(Schedulers.io())
                                                .subscribeOn(Schedulers.io())
                                                .subscribe {
                                                    getAllPlayers.execute(clubInfo)
                                                            .observeOn(Schedulers.computation())
                                                            .subscribeOn(Schedulers.computation())
                                                }
                                    }
                        }
            }
}