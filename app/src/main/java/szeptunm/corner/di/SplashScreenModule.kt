package szeptunm.corner.di

import dagger.Module
import dagger.Provides
import szeptunm.corner.ui.splashScreen.SplashScreenActivity
import javax.inject.Named

@Module
class SplashScreenModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        @Named("favourite")
        fun providesFavouriteClub(splashScreenActivity: SplashScreenActivity): String {
            return splashScreenActivity.favouriteClub
        }
    }
}