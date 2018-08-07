package szeptunm.corner.dataaccess.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import io.reactivex.Single
import szeptunm.corner.dataaccess.database.entity.StandingEntity

@Dao
interface StandingDao {

    @Delete
    fun delete(vararg standingEntity: StandingEntity)

    @Update
    fun update(vararg standingEntity: StandingEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStanding(standingEntity: StandingEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllStandings(standingEntityList:List<StandingEntity>)

    @Query("SELECT * FROM standing")
    fun getAllStandings(): Single<List<StandingEntity>>

    @Query("SELECT * FROM standing WHERE id LIKE :id")
    fun getStandingById(id: Int): Single<StandingEntity>

    @Query("SELECT * FROM standing WHERE currentMatchDay LIKE :matchDay")
    fun getStandingByMatchDay(matchDay: Int): Single<StandingEntity>

    @Query("SELECT * FROM standing WHERE currentMatchDay LIKE :matchDay AND id LIKE :id")
    fun getStandingByMatchDayAndId(matchDay: Int, id: Int):Single<StandingEntity>
}