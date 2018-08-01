package szeptunm.corner.dataaccess.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single
import szeptunm.corner.dataaccess.database.entity.Standing

@Dao
interface StandingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStanding(standing: Standing)

    @Query("SELECT * FROM standing")
    fun getAllStandings(): Single<List<Standing>>
}