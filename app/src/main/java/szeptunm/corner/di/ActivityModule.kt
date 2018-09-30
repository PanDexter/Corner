package szeptunm.corner.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import szeptunm.corner.ui.MainActivity
import szeptunm.corner.ui.news.NewsDetailActivity
import szeptunm.corner.ui.news.NewsFragment
import szeptunm.corner.ui.news.NewsWebActivity
import szeptunm.corner.ui.schedule.MatchFragment
import szeptunm.corner.ui.standing.StandingFragment
import szeptunm.corner.ui.team.PlayerDetailActivity
import szeptunm.corner.ui.team.TeamFragment

@Module
abstract class ActivityModule {


    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindNewsFragment(): NewsFragment

    @ContributesAndroidInjector
    abstract fun bindNewsDetailsFragment(): NewsDetailActivity

    @ContributesAndroidInjector
    abstract fun bindNewsWebFragment(): NewsWebActivity

    @ContributesAndroidInjector
    abstract fun bindTeamFragment(): TeamFragment

    @ContributesAndroidInjector
    abstract fun bindScheduleFragment(): MatchFragment

    @ContributesAndroidInjector
    abstract fun bindPlayerDetailsFragment(): PlayerDetailActivity

    @ContributesAndroidInjector
    abstract fun bindStandingFragment(): StandingFragment
}