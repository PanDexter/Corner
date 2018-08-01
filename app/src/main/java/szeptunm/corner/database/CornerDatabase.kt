package szeptunm.corner.database

import androidx.room.Database
import androidx.room.RoomDatabase
import szeptunm.corner.database.dao.FootballerDao
import szeptunm.corner.database.dao.StandingDao
import szeptunm.corner.database.dao.TeamDao
import szeptunm.corner.database.entity.Footballer
import szeptunm.corner.database.entity.Standing
import szeptunm.corner.database.entity.Team
import javax.inject.Singleton

@Singleton
@Database(entities =
[Footballer::class, Standing::class, Team::class], version = 1)
abstract class CornerDatabase : RoomDatabase() {

    abstract fun footballerDao(): FootballerDao
    abstract fun standingDao(): StandingDao
    abstract fun teamDao(): TeamDao
}