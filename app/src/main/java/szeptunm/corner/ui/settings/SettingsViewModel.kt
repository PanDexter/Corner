package szeptunm.corner.ui.settings

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import szeptunm.corner.commons.Constants.KEY_CLUB_NAME
import szeptunm.corner.commons.Preferences
import szeptunm.corner.domain.splashScreen.GetClubInfoByName
import javax.inject.Inject

class SettingsViewModel @Inject constructor(private var getClubInfoByName: GetClubInfoByName,
        private val preferences: Preferences) {

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun setNewClubInfo(clubName: String) {
        getClubInfoByName.execute(clubName)
                .subscribeOn(Schedulers.computation())
                .subscribe { club ->
                    preferences.putPreferenceString(KEY_CLUB_NAME, club.name)
                }.addTo(compositeDisposable)
    }
}