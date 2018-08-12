package szeptunm.corner.dataaccess.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import io.reactivex.Single
import szeptunm.corner.dataaccess.database.entity.NewsEntity

@Dao
interface NewsDao {



    @Update
    fun update(vararg team: NewsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(news: NewsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllNews(newsList: List<NewsEntity>)

    @Query("SELECT * FROM news")
    fun getAllNews(): Single<List<NewsEntity>>

    @Query("SELECT * FROM news WHERE teamId LIKE :id")
    fun getNewsByTeamId(id: Int): Single<List<NewsEntity>>

}