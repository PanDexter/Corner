package szeptunm.corner.domain

import com.nhaarman.mockito_kotlin.*
import io.reactivex.Observable
import org.junit.*
import org.mockito.*
import szeptunm.corner.ClubInfoMocker.mockClub
import szeptunm.corner.dataaccess.repository.MatchRepository
import szeptunm.corner.domain.schedule.GetTeamMatches
import szeptunm.corner.entity.Match
import java.util.Arrays
import kotlin.test.assertTrue

class GetTeamMatchesTest {

    @Mock
    lateinit var repository: MatchRepository

    lateinit var getTeamMatches: GetTeamMatches

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getTeamMatches = GetTeamMatches(repository)
    }

    @Test
    fun `get team matches test`() {
        //GIVEN
        val clubInfo = mockClub("Test", "", 1)
        val matches = mockMatches()
        whenever(repository.getAllMatches(clubInfo)).thenReturn(Observable.just(matches))

        //WHEN
        val result = getTeamMatches.execute(clubInfo)

        //THEN
        result.test().assertResult(matches)

        assertTrue {
            result.test().values()[0].size == matches.size
        }
    }

    private fun mockMatches() = Arrays.asList(
            Match(0, 0, 0, 0, 0, 0, 1, 2, "", 0),
            Match(0, 0, 0, 0, 0, 0, 3, 1, "", 0))
}