package szeptunm.corner.domain.splashScreen

import io.reactivex.Completable
import szeptunm.corner.domain.news.GetNewsFromApi
import szeptunm.corner.domain.players.GetTeamFromApi
import szeptunm.corner.domain.schedule.GetMatchesFromApi
import szeptunm.corner.domain.standings.GetStandingFromApi
import szeptunm.corner.entity.ClubInfo
import javax.inject.Inject

class InitializeData @Inject constructor() {

    @Inject
    lateinit var getTeamFromApi: GetTeamFromApi
    @Inject
    lateinit var getNewsFromApi: GetNewsFromApi
    @Inject
    lateinit var getMatchesFromApi: GetMatchesFromApi
    @Inject
    lateinit var getStandingFromApi: GetStandingFromApi

    fun execute(clubInfo: ClubInfo): Completable =
            getNewsFromApi.execute(clubInfo)
                    .andThen {
                        getNewsFromApi.execute(clubInfo)
                    }.andThen {
                        getTeamFromApi.execute(clubInfo)
                    }
//            }.andThen {
//                getTeamFromApi.execute(clubInfo)
//            }
}