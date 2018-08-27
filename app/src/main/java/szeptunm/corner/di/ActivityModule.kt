package szeptunm.corner.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import szeptunm.corner.ui.MainActivity
import szeptunm.corner.ui.news.NewsFragment
import szeptunm.corner.ui.team.TeamFragment

@Module
abstract class ActivityModule {


    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindNewsFragment(): NewsFragment

    @ContributesAndroidInjector
    abstract fun bindTeamFragment(): TeamFragment
}