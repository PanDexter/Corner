package szeptunm.corner.di

import androidx.room.Room
import dagger.Module
import dagger.Provides
import szeptunm.corner.App
import szeptunm.corner.dataaccess.database.CornerDatabase
import szeptunm.corner.dataaccess.database.dao.ClubInfoDao
import szeptunm.corner.dataaccess.database.dao.CompetitionDao
import szeptunm.corner.dataaccess.database.dao.MatchDao
import szeptunm.corner.dataaccess.database.dao.NewsDao
import szeptunm.corner.dataaccess.database.dao.PlayerDao
import szeptunm.corner.dataaccess.database.dao.StandingDao
import szeptunm.corner.dataaccess.database.dao.TeamDao
import javax.inject.Singleton

@Module
abstract class AppModule {


    @Module
    companion object {

        @Provides
        @JvmStatic
        @Singleton
        fun providesFootballerDao(database: CornerDatabase): PlayerDao = database.footballerDao()

        @Provides
        @JvmStatic
        @Singleton
        fun providesTeamDao(database: CornerDatabase): TeamDao = database.teamDao()

        @Provides
        @JvmStatic
        @Singleton
        fun providesStandingDao(database: CornerDatabase): StandingDao = database.standingDao()

        @Provides
        @JvmStatic
        @Singleton
        fun providesCompetitionDao(database: CornerDatabase): CompetitionDao = database.competitionDao()

        @Provides
        @JvmStatic
        @Singleton
        fun providesMatchDao(database: CornerDatabase):MatchDao = database.matchDao()

        @Provides
        @JvmStatic
        @Singleton
        fun providesNewsDao(database: CornerDatabase):NewsDao = database.newsDao()

        @Provides
        @JvmStatic
        @Singleton
        fun providesClubInfoDao(database: CornerDatabase): ClubInfoDao = database.clubInfoDao()

        @Provides
        @JvmStatic
        @Singleton
        fun providesCornerDatabase(app: App): CornerDatabase = Room.databaseBuilder(app.applicationContext,
                CornerDatabase::class.java, "cornerdb").build()
    }
}