package szeptunm.corner.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import szeptunm.corner.ui.MainActivity
import szeptunm.corner.ui.news.NewsDetailActivity
import szeptunm.corner.ui.news.NewsWebActivity
import szeptunm.corner.ui.schedule.MatchDetailActivity
import szeptunm.corner.ui.splashScreen.SplashScreenActivity
import szeptunm.corner.ui.team.PlayerDetailActivity

@Module
abstract class ActivityModule {


    @ContributesAndroidInjector(modules = [FragmentsModule::class, MainActivityModule::class])
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindSplashScreenActivity(): SplashScreenActivity

    @ContributesAndroidInjector
    abstract fun bindNewsDetailsActivity(): NewsDetailActivity

    @ContributesAndroidInjector
    abstract fun bindNewsWebActivity(): NewsWebActivity

    @ContributesAndroidInjector
    abstract fun bindPlayerDetailsActivity(): PlayerDetailActivity

    @ContributesAndroidInjector
    abstract fun bindMatchDetailsActivity(): MatchDetailActivity
}