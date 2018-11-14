package szeptunm.corner.domain

import com.nhaarman.mockito_kotlin.*
import io.reactivex.Single
import org.junit.*
import org.mockito.*
import szeptunm.corner.dataaccess.repository.MatchRepository
import szeptunm.corner.domain.competitions.GetCompetitionById
import szeptunm.corner.entity.Competition

class GetCompetitionByIdTest {

    lateinit var getCompetitionById: GetCompetitionById

    @Mock
    lateinit var repository: MatchRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getCompetitionById = GetCompetitionById(repository)
    }

    @Test
    fun `get competition by id test`() {
        //GIVEN
        val competitions = mockCompetitions()
        whenever(repository.getCompetitionById(1)).thenReturn(Single.just(competitions))

        //WHEN
        val result = getCompetitionById.execute(1)

        //THEN
        result.test().assertResult(competitions)
    }

    private fun mockCompetitions() =
            Competition(1, "Test1")
}