package szeptunm.corner.dataaccess.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import io.reactivex.Single
import szeptunm.corner.dataaccess.database.entity.StandingValue

@Dao
interface StandingValueDao {

    @Delete
    fun delete(vararg standingValues: StandingValue)

    @Update
    fun update(vararg standingValues: StandingValue)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStandingValue(standingValue: StandingValue)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllStandingValues(standingValueList:List<StandingValue>)

    @Query("SELECT * FROM standingValues")
    fun getAllStandingValues(): Single<List<StandingValue>>

    @Query("SELECT * FROM standingValues WHERE id LIKE :id")
    fun getStandingValueById(id: Int): Single<StandingValue>

    @Query("SELECT * FROM standingValues WHERE competitionId LIKE :competitionId")
    fun getStandingValueByCompetitionId(competitionId:Int):Single<List<StandingValue>>
}