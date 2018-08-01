package szeptunm.corner.dataaccess.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single
import szeptunm.corner.dataaccess.database.entity.Footballer

@Dao
interface FootballerDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(footballerList: List<Footballer>)

    @Query("SELECT * FROM footbaler")
    fun getAllFootballers(): Single<List<Footballer>>

    @Query("SELECT * FROM footbaler WHERE position LIKE :position")
    fun getFootballerByPosition(position:String):Single<List<Footballer>>

    @Query("SELECT * FROM footbaler WHERE club LIKE :club")
    fun getFootballerByClub(club:String):Single<List<Footballer>>
}