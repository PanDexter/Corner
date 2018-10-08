package szeptunm.corner.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import szeptunm.corner.ui.MainActivity
import szeptunm.corner.ui.news.NewsDetailActivity
import szeptunm.corner.ui.news.NewsWebActivity
import szeptunm.corner.ui.splashScreen.SplashScreenActivity
import szeptunm.corner.ui.team.PlayerDetailActivity

@Module
abstract class ActivityModule {


    @ContributesAndroidInjector(modules = [FragmentsModule::class, MainActivityModule::class])
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindSplashScreenActivity(): SplashScreenActivity

    @ContributesAndroidInjector
    abstract fun bindNewsDetailsFragment(): NewsDetailActivity

    @ContributesAndroidInjector
    abstract fun bindNewsWebFragment(): NewsWebActivity

    @ContributesAndroidInjector
    abstract fun bindPlayerDetailsFragment(): PlayerDetailActivity
}