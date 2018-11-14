package szeptunm.corner.domain

import com.nhaarman.mockito_kotlin.*
import io.reactivex.Observable
import org.junit.*
import org.mockito.*
import szeptunm.corner.ClubInfoMocker.mockClub
import szeptunm.corner.dataaccess.repository.PlayerRepository
import szeptunm.corner.domain.players.GetTeamPlayers
import szeptunm.corner.entity.Player
import java.util.Arrays

class GetTeamPlayersTest {

    @Mock
    lateinit var repository: PlayerRepository

    lateinit var getTeamPlayers: GetTeamPlayers

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getTeamPlayers = GetTeamPlayers(repository)
    }

    @Test
    fun `get team players test`() {
        //GIVEN
        val clubInfo = mockClub("", "", 1)
        val players = mockPlayers()
        whenever(repository.getAllPlayers(clubInfo)).thenReturn(Observable.just(players))

        //WHEN
        val result = getTeamPlayers.execute(clubInfo)

        //THEN
        result.test().assertResult(players)
    }

    private fun mockPlayers() = Arrays.asList(
            Player("", "", "", "", 1),
            Player("", "", "", "", 1),
            Player("", "", "", "", 1),
            Player("", "", "", "", 1)
    )
}