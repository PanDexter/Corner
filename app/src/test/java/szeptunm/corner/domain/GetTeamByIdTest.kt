package szeptunm.corner.domain

import com.nhaarman.mockito_kotlin.*
import io.reactivex.Single
import org.junit.*
import org.mockito.*
import szeptunm.corner.dataaccess.repository.MatchRepository
import szeptunm.corner.domain.teams.GetTeamById
import szeptunm.corner.entity.Team

class GetTeamByIdTest() {

    @Mock
    lateinit var matchRepository: MatchRepository

    lateinit var getTeamById: GetTeamById

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getTeamById = GetTeamById(matchRepository)
    }

    @Test
    fun `get team by id test`() {
        //GIVEN
        val team = mockTeam()
        whenever(matchRepository.getTeamById(mockTeam().id)).thenReturn(Single.just(team))

        //WHEN
        val result = getTeamById.execute(mockTeam().id)

        //THEN
        result.test().assertResult(team)
    }

    private fun mockTeam() = Team(1, "Test")
}