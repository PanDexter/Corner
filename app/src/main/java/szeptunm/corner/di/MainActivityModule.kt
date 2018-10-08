package szeptunm.corner.di

import dagger.Module
import dagger.Provides
import szeptunm.corner.entity.ClubInfo
import szeptunm.corner.ui.MainActivity

@Module
abstract class MainActivityModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun providesClubInfo(mainActivity: MainActivity): ClubInfo {
            return mainActivity.clubInfo
        }
    }
}