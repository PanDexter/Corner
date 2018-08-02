package szeptunm.corner.dataaccess.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single
import szeptunm.corner.dataaccess.database.entity.Competition
import szeptunm.corner.dataaccess.database.entity.Team

@Dao
interface CompetitionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCompetition(competition: Competition)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCompetitions(competitionList:List<Competition>)

    @Query("SELECT * FROM competition")
    fun getAllCompetitions(): Single<List<Competition>>

    @Query("SELECT * FROM competition WHERE id LIKE :id")
    fun getCompetitionById(id: Int): Single<Competition>

    @Query("SELECT * FROM competition WHERE name LIKE:name")
    fun getCompetitionyName(name:String): Single<Competition>

}