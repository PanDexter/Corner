package szeptunm.corner.dataaccess.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import io.reactivex.Single
import szeptunm.corner.dataaccess.database.entity.FootballerEntity

@Dao
interface FootballerDao {

    @Update
    fun update(vararg footballerEntity: FootballerEntity)

    @Delete
    fun delete(vararg footballerEntity: FootballerEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFootballer(footballerEntity: FootballerEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllFootballers(footballerEntityList: List<FootballerEntity>)

    @Query("SELECT * FROM footballer")
    fun getAllFootballers(): Single<List<FootballerEntity>>

    @Query("SELECT * FROM footballer WHERE position LIKE :position")
    fun getFootballerByPosition(position: String): Single<List<FootballerEntity>>

    @Query("SELECT * FROM footballer WHERE teamId LIKE :club")
    fun getFootballerByClub(club: String): Single<List<FootballerEntity>>
}