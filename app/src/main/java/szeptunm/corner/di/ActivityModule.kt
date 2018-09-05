package szeptunm.corner.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import szeptunm.corner.ui.MainActivity
import szeptunm.corner.ui.news.NewsDetailFragment
import szeptunm.corner.ui.news.NewsFragment
import szeptunm.corner.ui.news.NewsWebFragment
import szeptunm.corner.ui.schedule.MatchFragment
import szeptunm.corner.ui.team.PlayerDetailFragment
import szeptunm.corner.ui.team.TeamFragment

@Module
abstract class ActivityModule {


    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindNewsFragment(): NewsFragment

    @ContributesAndroidInjector
    abstract fun bindNewsDetailsFragment(): NewsDetailFragment

    @ContributesAndroidInjector
    abstract fun bindNewsWebFragment(): NewsWebFragment

    @ContributesAndroidInjector
    abstract fun bindTeamFragment(): TeamFragment

    @ContributesAndroidInjector
    abstract fun bindScheduleFragment(): MatchFragment

    @ContributesAndroidInjector
    abstract fun bindPlayerDetailsFragment(): PlayerDetailFragment
}