package szeptunm.corner.dataaccess.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single
import szeptunm.corner.dataaccess.database.entity.CompetitionEntity

@Dao
interface CompetitionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCompetition(competitionEntity: CompetitionEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCompetitions(competitionEntityList:List<CompetitionEntity>)

    @Query("SELECT * FROM competition")
    fun getAllCompetitions(): Single<List<CompetitionEntity>>

    @Query("SELECT * FROM competition WHERE id LIKE :id")
    fun getCompetitionById(id: Int): Single<CompetitionEntity>

    @Query("SELECT * FROM competition WHERE name LIKE:name")
    fun getCompetitionyName(name:String): Single<CompetitionEntity>

}