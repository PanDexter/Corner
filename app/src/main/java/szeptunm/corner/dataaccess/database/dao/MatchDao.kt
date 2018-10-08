package szeptunm.corner.dataaccess.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single
import szeptunm.corner.dataaccess.database.entity.MatchEntity

@Dao
interface MatchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMatch(matchEntity: MatchEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllMatches(matchEntityList: List<MatchEntity>)

    @Query("SELECT * FROM `match`")
    fun getAllMatches(): Single<List<MatchEntity>>

    @Query("SELECT * FROM `match` WHERE homeTeam =:teamId OR awayTeam =:teamId")
    fun getMatchByTeamId(teamId: Int): Single<List<MatchEntity>>
}