package szeptunm.corner.dataaccess.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single
import szeptunm.corner.dataaccess.database.entity.ClubInfoEntity

@Dao
interface ClubInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllClubInfo(clubInfoList: List<ClubInfoEntity>)

    @Query("SELECT * FROM clubInfo")
    fun getAllClubInfo(): Single<List<ClubInfoEntity>>

    @Query("SELECT * FROM clubInfo WHERE name=:club")
    fun getClubInfoByName(club: String): Single<ClubInfoEntity>
}