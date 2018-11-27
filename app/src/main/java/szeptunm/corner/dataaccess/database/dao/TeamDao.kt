package szeptunm.corner.dataaccess.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single
import szeptunm.corner.dataaccess.database.entity.TeamEntity

@Dao
interface TeamDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTeam(team: TeamEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllTeams(teamList: List<TeamEntity>)

    @Query("SELECT * FROM team")
    fun getAllTeams(): Single<List<TeamEntity>>

    @Query("SELECT * FROM team WHERE name LIKE:club")
    fun getTeamByName(club: String): Single<TeamEntity>

    @Query("SELECT * FROM team WHERE id = :id")
    fun getTeamById(id: Int): Single<TeamEntity>
}