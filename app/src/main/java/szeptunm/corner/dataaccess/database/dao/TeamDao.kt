package szeptunm.corner.dataaccess.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single
import szeptunm.corner.dataaccess.database.entity.Team

@Dao
interface TeamDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTeam(team: Team)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllTeams(teamList: List<Team>)

    @Query("SELECT * FROM team")
    fun getAllTeams(): Single<List<Team>>

    @Query("SELECT * FROM team WHERE name LIKE :name")
    fun getTeamByName(name: String): Single<Team>
}