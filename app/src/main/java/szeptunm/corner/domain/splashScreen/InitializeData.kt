package szeptunm.corner.domain.splashScreen

import io.reactivex.Completable
import szeptunm.corner.domain.news.GetAllNews
import szeptunm.corner.domain.players.GetAllPlayers
import szeptunm.corner.domain.schedule.GetAllMatches
import szeptunm.corner.domain.standings.GetAllStandings
import szeptunm.corner.entity.ClubInfo
import szeptunm.corner.entity.Match
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
            Completable.fromObservable<List<Match>> { getAllMatches.execute() }
                    .andThen { getAllStandings.execute(clubInfo) }
                    .andThen { getAllNews.execute(clubInfo) }
                    .andThen { getAllPlayers.execute(clubInfo.teamApiId) }
}