package szeptunm.corner.dataaccess.repository

import io.reactivex.Single
import szeptunm.corner.dataaccess.database.dao.FootballerDao
import szeptunm.corner.dataaccess.database.entity.Footballer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FootballerRepositoryImpl @Inject constructor(var footballerDao: FootballerDao) :
        FootballerRepository {

    override fun insertAll(footballerList: List<Footballer>) = footballerDao.insertAll(footballerList)

    override fun getAllFootballers(): Single<List<Footballer>> = footballerDao.getAllFootballers()

    override fun getFootballerByPosition(
            position: String): Single<List<Footballer>> = footballerDao.getFootballerByPosition(position)

    override fun getFootballerByClub(club: String): Single<List<Footballer>> = footballerDao.getFootballerByClub(club)
}