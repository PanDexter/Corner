package szeptunm.corner.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import szeptunm.corner.ui.news.NewsFragment
import szeptunm.corner.ui.schedule.MatchFragment
import szeptunm.corner.ui.settings.SettingsFragment
import szeptunm.corner.ui.standing.StandingFragment
import szeptunm.corner.ui.team.TeamFragment

@Module
abstract class FragmentsModule {

    @ContributesAndroidInjector
    abstract fun bindNewsFragment(): NewsFragment

    @ContributesAndroidInjector
    abstract fun bindTeamFragment(): TeamFragment

    @ContributesAndroidInjector
    abstract fun bindScheduleFragment(): MatchFragment

    @ContributesAndroidInjector
    abstract fun bindStandingFragment(): StandingFragment

    @ContributesAndroidInjector
    abstract fun bindSettingsFragment(): SettingsFragment
}