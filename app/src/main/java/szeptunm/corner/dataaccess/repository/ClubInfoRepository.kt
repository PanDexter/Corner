package szeptunm.corner.dataaccess.repository

import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.SingleTransformer
import szeptunm.corner.dataaccess.database.dao.ClubInfoDao
import szeptunm.corner.dataaccess.database.entity.ClubInfoEntity
import szeptunm.corner.entity.ClubInfo
import javax.inject.Inject

class ClubInfoRepository @Inject constructor(private var clubInfoDao: ClubInfoDao) {


    private val clubInfoTransformer: SingleTransformer<List<ClubInfoEntity>, List<ClubInfo>> =
            SingleTransformer { upstream ->
                upstream.flattenAsObservable { list -> list }
                        .map { ClubInfo(it) }
                        .toList()
            }

    fun getClubInfoFromDb(): Single<List<ClubInfo>> =
            clubInfoDao.getAllClubInfo()
                    .compose(clubInfoTransformer)

    fun getClubInfoByName(clubName: String) = clubInfoDao.getClubInfoByName(clubName)

    fun saveClubInfoToDatabase(): Completable =
            Completable.fromCallable {
                clubInfoDao.insertAllClubInfo(prepareClubList())
            }

    private fun prepareClubList(): List<ClubInfoEntity> =
            listOf(
                    ClubInfoEntity(
                            name = "FC Barcelona",
                            newsUrl = "http://www.espnfc.com/club/barcelona/83/rss",
                            teamApiId = 133739,
                            matchTeamId = 81,
                            competitionId = 2014),
                    ClubInfoEntity(
                            name = "Real Madrid",
                            newsUrl = "http://www.espnfc.com/club/real-madrid/86/rss",
                            teamApiId = 133738,
                            matchTeamId = 86,
                            competitionId = 2014),
                    ClubInfoEntity(
                            name = "Arsenal London",
                            newsUrl = "http://www.espnfc.com/club/arsenal/359/rss",
                            teamApiId = 133604,
                            matchTeamId = 57,
                            competitionId = 2021),
                    ClubInfoEntity(
                            name = "Chelsea London",
                            newsUrl = "http://www.espnfc.com/club/chelsea/363/rss",
                            teamApiId = 133610,
                            matchTeamId = 61,
                            competitionId = 2021),
                    ClubInfoEntity(
                            name = "Bayern Munich",
                            newsUrl = "http://www.espnfc.com/club/bayern-munich/132/rss",
                            teamApiId = 133664,
                            matchTeamId = 5,
                            competitionId = 2002),
                    ClubInfoEntity(
                            name = "Liverpool",
                            newsUrl = "http://www.espnfc.com/club/liverpool/364/rss",
                            teamApiId = 133602,
                            matchTeamId = 64,
                            competitionId = 2021),
                    ClubInfoEntity(
                            name = "Manchester City",
                            newsUrl = "http://www.espnfc.com/club/manchester-city/382/rss",
                            teamApiId = 133613,
                            matchTeamId = 65,
                            competitionId = 2021),
                    ClubInfoEntity(
                            name = "Manchester United",
                            newsUrl = "http://www.espnfc.com/club/manchester-united/360/rss",
                            teamApiId = 133612,
                            matchTeamId = 66,
                            competitionId = 2021),
                    ClubInfoEntity(
                            name = "Atletico Madrid",
                            newsUrl = "http://www.espnfc.com/club/atletico-madrid/1068/rss",
                            teamApiId = 133729,
                            matchTeamId = 78,
                            competitionId = 2014),
                    ClubInfoEntity(
                            name = "Borussia Dortmund",
                            newsUrl = "http://www.espnfc.com/club/borussia-dortmund/124/rss",
                            teamApiId = 133650,
                            matchTeamId = 4,
                            competitionId = 2002),
                    ClubInfoEntity(
                            name = "Juventus Turin",
                            newsUrl = "http://www.espnfc.com/club/juventus/111/rss",
                            teamApiId = 133676,
                            matchTeamId = 109,
                            competitionId = 2019),
                    ClubInfoEntity(
                            name = "Paris Saint Germain",
                            newsUrl = "http://www.espnfc.com/club/paris-saint-germain/160/rss",
                            teamApiId = 133714,
                            matchTeamId = 524,
                            competitionId = 2015)
            )
}