package szeptunm.corner.dataaccess.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import io.reactivex.Single
import szeptunm.corner.dataaccess.database.entity.MatchEntity

@Dao
interface MatchDao {

    @Delete
    fun delete(vararg matchEntities: MatchEntity)

    @Update
    fun update(vararg matchEntities: MatchEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMatch(matchEntity: MatchEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllMatches(matchEntityList: List<MatchEntity>)

    @Query("SELECT * FROM `match`")
    fun getAllMatches(): Single<List<MatchEntity>>

    @Query("SELECT * FROM `match` WHERE id LIKE :id")
    fun getMatchById(id: Int): Single<MatchEntity>

    @Query("SELECT * FROM `match` WHERE homeTeamId OR awayTeamId LIKE:teamId")
    fun getMatchByTeamId(teamId: Int): Single<List<MatchEntity>>
}