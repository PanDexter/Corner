package szeptunm.corner.domain

import com.nhaarman.mockito_kotlin.*
import io.reactivex.Observable
import org.junit.*
import org.mockito.*
import szeptunm.corner.ClubInfoMocker.mockClub
import szeptunm.corner.dataaccess.repository.StandingRepository
import szeptunm.corner.domain.standings.GetAllStandings
import szeptunm.corner.entity.Standing
import java.util.Arrays

class GetAllStandingsTest {

    @Mock
    lateinit var standingRepository: StandingRepository

    lateinit var getAllStandings: GetAllStandings

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getAllStandings = GetAllStandings(standingRepository)
    }

    @Test
    fun `get standings test`() {
        //GIVEN
        val standing = mockStanding()
        val club = mockClub()
        whenever(standingRepository.getAllStandings(club)).thenReturn(Observable.just(standing))

        //WHEN
        val result = getAllStandings.execute(club)

        //THEN
        result.test().assertResult(standing)
    }

    private fun mockStanding(): List<Standing> = Arrays.asList(
            Standing(1, 1, 1, 1, 1, 1, 1, 1, 1, 1),
            Standing(2, 2, 2, 2, 2, 2, 2, 2, 2, 2))
}