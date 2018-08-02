package szeptunm.corner.dataaccess.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import io.reactivex.Single
import szeptunm.corner.dataaccess.database.entity.Match

@Dao
interface MatchDao {

    @Delete
    fun delete(vararg match: Match)

    @Update
    fun update(vararg match: Match)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMatch(match: Match)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllMatches(matchList: List<Match>)

    @Query("SELECT * FROM `match`")
    fun getAllMatches(): Single<List<Match>>

    @Query("SELECT * FROM `match` WHERE id LIKE :id")
    fun getMatchById(id: Int): Single<Match>

    @Query("SELECT * FROM `match` WHERE homeTeamId OR awayTeamId LIKE:teamId")
    fun getMatchByTeamId(teamId: Int): Single<List<Match>>
}