package szeptunm.corner

import com.nhaarman.mockito_kotlin.*
import org.mockito.*
import szeptunm.corner.entity.ClubInfo

object ClubInfoMocker {

    @JvmOverloads
    fun mockClub(name: String = "",
            newsUrl: String = "",
            teamApiId: Int = 0,
            matchTeamId: Int = 0,
            competitonId: Int = 0,
            gradient: Int = 0,
            badge: Int = 0): ClubInfo =
            Mockito.mock(ClubInfo::class.java).apply {
                whenever(this.name).thenReturn(name)
                whenever(this.newsUrl).thenReturn(newsUrl)
                whenever(this.teamApiId).thenReturn(teamApiId)
                whenever(this.matchTeamId).thenReturn(matchTeamId)
                whenever(this.competitionId).thenReturn(competitonId)
                whenever(this.gradient).thenReturn(gradient)
                whenever(this.badge).thenReturn(badge)
            }
}