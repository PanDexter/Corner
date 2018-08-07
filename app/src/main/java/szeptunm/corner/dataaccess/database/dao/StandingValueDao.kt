package szeptunm.corner.dataaccess.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import io.reactivex.Single
import szeptunm.corner.dataaccess.database.entity.StandingValueEntity

@Dao
interface StandingValueDao {

    @Delete
    fun delete(vararg standingValueEntities: StandingValueEntity)

    @Update
    fun update(vararg standingValueEntities: StandingValueEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStandingValue(standingValueEntity: StandingValueEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllStandingValues(standingValueEntityList:List<StandingValueEntity>)

    @Query("SELECT * FROM standingValues")
    fun getAllStandingValues(): Single<List<StandingValueEntity>>

    @Query("SELECT * FROM standingValues WHERE id LIKE :id")
    fun getStandingValueById(id: Int): Single<StandingValueEntity>

    @Query("SELECT * FROM standingValues WHERE competitionId LIKE :competitionId")
    fun getStandingValueByCompetitionId(competitionId:Int):Single<List<StandingValueEntity>>
}