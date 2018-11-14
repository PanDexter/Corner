package szeptunm.corner.domain

import com.nhaarman.mockito_kotlin.*
import io.reactivex.Single
import org.junit.*
import org.mockito.*
import szeptunm.corner.ClubInfoMocker.mockClub
import szeptunm.corner.dataaccess.repository.ClubInfoRepository
import szeptunm.corner.domain.splashScreen.GetClubInfoByName

class GetClubInfoByNameTest {

    @Mock
    lateinit var repository: ClubInfoRepository

    lateinit var getClubInfoByName: GetClubInfoByName

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getClubInfoByName = GetClubInfoByName(repository)
    }

    @Test
    fun `get club info by name test`() {
        //GIVEN
        val clubInfo = mockClub("Test")
        whenever(repository.getClubInfoByName("Test")).thenReturn(Single.just(clubInfo))

        //WHEN
        val result = getClubInfoByName.execute("Test")

        //THEN
        result.test().assertResult(clubInfo)
    }
}