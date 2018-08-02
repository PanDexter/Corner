package szeptunm.corner.dataaccess.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import io.reactivex.Single
import szeptunm.corner.dataaccess.database.entity.Standing

@Dao
interface StandingDao {

    @Delete
    fun delete(vararg standing: Standing)

    @Update
    fun update(vararg standing: Standing)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStanding(standing: Standing)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllStandings(standingList:List<Standing>)

    @Query("SELECT * FROM standing")
    fun getAllStandings(): Single<List<Standing>>

    @Query("SELECT * FROM standing WHERE id LIKE :id")
    fun getStandingById(id: Int): Single<Standing>

    @Query("SELECT * FROM standing WHERE currentMatchDay LIKE :matchDay")
    fun getStandingByMatchDay(matchDay: Int): Single<Standing>

    @Query("SELECT * FROM standing WHERE currentMatchDay LIKE :matchDay AND id LIKE :id")
    fun getStandingByMatchDayAndId(matchDay: Int, id: Int):Single<Standing>
}