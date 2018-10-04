package szeptunm.corner.dataaccess.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single
import szeptunm.corner.dataaccess.database.entity.PlayerEntity

@Dao
interface PlayerDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlayer(PlayerEntity: PlayerEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPlayers(PlayerEntityList: List<PlayerEntity>)

    @Query("SELECT * FROM Player")
    fun getAllPlayers(): Single<List<PlayerEntity>>

    @Query("SELECT * FROM Player WHERE position LIKE :position")
    fun getPlayerByPosition(position: String): Single<List<PlayerEntity>>

    @Query("SELECT * FROM Player WHERE teamId =:club")
    fun getPlayerByClub(club: Int): Single<List<PlayerEntity>>
}