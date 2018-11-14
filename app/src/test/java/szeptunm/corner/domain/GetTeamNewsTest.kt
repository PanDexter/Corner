package szeptunm.corner.domain

import com.nhaarman.mockito_kotlin.*
import io.reactivex.Observable
import org.junit.*
import org.mockito.*
import szeptunm.corner.ClubInfoMocker.mockClub
import szeptunm.corner.dataaccess.repository.NewsRepository
import szeptunm.corner.domain.news.GetTeamNews
import szeptunm.corner.entity.News
import java.util.Arrays

class GetTeamNewsTest {

    lateinit var getTeamNews: GetTeamNews

    @Mock
    lateinit var repository: NewsRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getTeamNews = GetTeamNews(repository)
    }

    @Test
    fun `get team news test`() {
        //GIVEN
        val clubInfo = mockClub()
        val news = mockNews()
        whenever(repository.getAllNews(clubInfo)).thenReturn(Observable.just(news))

        //WHEN
        val result = getTeamNews.execute(clubInfo)

        //THEN
        result.test().assertResult(news)
    }

    fun mockNews() = Arrays.asList(
            News("", "", "", "", "", 1),
            News("", "", "", "", "", 1),
            News("", "", "", "", "", 1),
            News("", "", "", "", "", 1)
    )
}