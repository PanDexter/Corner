package szeptunm.corner.dataaccess.repository.interfaces

import io.reactivex.Single
import szeptunm.corner.dataaccess.database.entity.Footballer

interface FootballerRepository {

    fun insertFootballer(footballer: Footballer)
    fun insertAllFootballers(footballerList: List<Footballer>)
    fun getAllFootballers(): Single<List<Footballer>>
    fun getFootballerByPosition(position: String): Single<List<Footballer>>
    fun getFootballerByClub(club: String): Single<List<Footballer>>
}