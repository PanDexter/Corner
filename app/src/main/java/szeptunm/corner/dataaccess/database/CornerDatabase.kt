package szeptunm.corner.dataaccess.database

import androidx.room.Database
import androidx.room.RoomDatabase
import szeptunm.corner.dataaccess.database.dao.CompetitionDao
import szeptunm.corner.dataaccess.database.dao.MatchDao
import szeptunm.corner.dataaccess.database.dao.NewsDao
import szeptunm.corner.dataaccess.database.dao.PlayerDao
import szeptunm.corner.dataaccess.database.dao.StandingDao
import szeptunm.corner.dataaccess.database.dao.TeamDao
import szeptunm.corner.dataaccess.database.entity.CompetitionEntity
import szeptunm.corner.dataaccess.database.entity.MatchEntity
import szeptunm.corner.dataaccess.database.entity.NewsEntity
import szeptunm.corner.dataaccess.database.entity.PlayerEntity
import szeptunm.corner.dataaccess.database.entity.StandingEntity
import szeptunm.corner.dataaccess.database.entity.TeamEntity
import javax.inject.Singleton

@Singleton
@Database(entities =
[PlayerEntity::class, TeamEntity::class, CompetitionEntity::class, MatchEntity::class, StandingEntity::class, NewsEntity::class],
        version = 1)
abstract class CornerDatabase : RoomDatabase() {

    abstract fun footballerDao(): PlayerDao
    abstract fun standingDao(): StandingDao
    abstract fun teamDao(): TeamDao
    abstract fun competitionDao(): CompetitionDao
    abstract fun matchDao(): MatchDao
    abstract fun newsDao():NewsDao
}