package szeptunm.corner.di

import androidx.room.Database
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import szeptunm.corner.App
import szeptunm.corner.dataaccess.database.CornerDatabase
import szeptunm.corner.dataaccess.database.dao.FootballerDao
import szeptunm.corner.dataaccess.database.dao.StandingDao
import szeptunm.corner.dataaccess.database.dao.TeamDao
import szeptunm.corner.dataaccess.repository.FootballerRepository
import szeptunm.corner.dataaccess.repository.FootballerRepositoryImpl
import szeptunm.corner.dataaccess.repository.TeamRepository
import szeptunm.corner.dataaccess.repository.TeamRepositoryImpl
import javax.inject.Singleton

@Module
abstract class AppModule {

    @Binds
    abstract fun providesFootballRepository(footballerRepositoryImpl: FootballerRepositoryImpl): FootballerRepository

    @Binds
    abstract fun providesTeamRepository(teamRepositoryImpl: TeamRepositoryImpl): TeamRepository

    @Module
    companion object {

        @Provides
        @JvmStatic
        @Singleton
        fun providesFootballerDao(database: CornerDatabase): FootballerDao = database.footballerDao()

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
        fun providesCornerDatabase(app: App): CornerDatabase = Room.databaseBuilder(app.applicationContext,
                CornerDatabase::class.java, "cornerdb").build()
    }
}