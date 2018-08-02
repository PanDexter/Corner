package szeptunm.corner.dataaccess.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import io.reactivex.Single
import szeptunm.corner.dataaccess.database.entity.Footballer

@Dao
interface FootballerDao {

    @Update
    fun update(vararg footballer: Footballer)

    @Delete
    fun delete(vararg footballer: Footballer)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFootballer(footballer: Footballer)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllFootballers(footballerList: List<Footballer>)

    @Query("SELECT * FROM footballer")
    fun getAllFootballers(): Single<List<Footballer>>

    @Query("SELECT * FROM footballer WHERE position LIKE :position")
    fun getFootballerByPosition(position: String): Single<List<Footballer>>

    @Query("SELECT * FROM footballer WHERE teamId LIKE :club")
    fun getFootballerByClub(club: String): Single<List<Footballer>>
}