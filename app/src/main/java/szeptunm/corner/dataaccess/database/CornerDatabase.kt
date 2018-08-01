package szeptunm.corner.dataaccess.database

import androidx.room.Database
import androidx.room.RoomDatabase
import szeptunm.corner.dataaccess.database.dao.FootballerDao
import szeptunm.corner.dataaccess.database.dao.StandingDao
import szeptunm.corner.dataaccess.database.dao.TeamDao
import szeptunm.corner.dataaccess.database.entity.Footballer
import szeptunm.corner.dataaccess.database.entity.Standing
import szeptunm.corner.dataaccess.database.entity.Team
import javax.inject.Singleton

@Singleton
@Database(entities =
[Footballer::class, Standing::class, Team::class], version = 1)
abstract class CornerDatabase : RoomDatabase() {

    abstract fun footballerDao(): FootballerDao
    abstract fun standingDao(): StandingDao
    abstract fun teamDao(): TeamDao
}