package szeptunm.corner.dataaccess.repository.implementations

import io.reactivex.Single
import szeptunm.corner.dataaccess.database.dao.FootballerDao
import szeptunm.corner.dataaccess.database.entity.FootballerEntity
import javax.inject.Inject

class FootballerRepositoryImpl @Inject constructor(var footballerDao: FootballerDao) {

    fun insertFootballer(footballerEntity: FootballerEntity) = footballerDao.insertFootballer(footballerEntity)

    fun insertAllFootballers(footballerEntityList: List<FootballerEntity>) = footballerDao.insertAllFootballers(footballerEntityList)

    fun getAllFootballers(): Single<List<FootballerEntity>> = footballerDao.getAllFootballers()

    fun getFootballerByPosition(
            position: String): Single<List<FootballerEntity>> = footballerDao.getFootballerByPosition(position)

    fun getFootballerByClub(club: String): Single<List<FootballerEntity>> = footballerDao.getFootballerByClub(club)
}