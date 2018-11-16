package szeptunm.corner.ui.splashScreen

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import szeptunm.corner.commons.Constants.KEY_CLUB_FAVOURITE
import szeptunm.corner.commons.Constants.KEY_CLUB_ID
import szeptunm.corner.commons.Constants.KEY_CLUB_NAME
import szeptunm.corner.commons.Preferences
import szeptunm.corner.domain.splashScreen.GetClubInfoByName
import szeptunm.corner.domain.splashScreen.GetFavouriteTeam
import szeptunm.corner.domain.splashScreen.InitializeClubInfo
import szeptunm.corner.domain.splashScreen.InitializeData
import szeptunm.corner.domain.splashScreen.IsFirstInitialized
import szeptunm.corner.entity.ClubInfo
import timber.log.Timber
import java.util.concurrent.TimeUnit.SECONDS
import javax.inject.Inject
import javax.inject.Named

class SplashScreenViewModel @Inject constructor(private val initializeClubInfo: InitializeClubInfo,
        private val initializeData: InitializeData, private val getClubInfoByName: GetClubInfoByName,
        private val preferences: Preferences, private val isFirstInitialized: IsFirstInitialized,
        private val getFavouriteTeam: GetFavouriteTeam, private val getClubInfoFromPrefs: GetClubInfoFromPrefs,
        @Named("favourite") val favouriteTeam: String) {

    private var subject: BehaviorSubject<ClubInfo> = BehaviorSubject.create()
    private var completeSubject: BehaviorSubject<Boolean> = BehaviorSubject.create()
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun observeCompletable(): Observable<Boolean> = completeSubject
    fun observeClubInfo(): Observable<ClubInfo> = subject

    fun init(isDuringFlow: Boolean) {
        if (isFirstInitialized.execute()) {
            initFirstTime()
            preferences.putPreferenceString(KEY_CLUB_FAVOURITE, favouriteTeam)
        } else {
            reinitApplication(isDuringFlow)
        }
    }

    private fun initFirstTime() {
        initializeClubInfo.execute()
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe {
                    getClubInfoByName.execute(favouriteTeam)
                            .observeOn(Schedulers.io())
                            .subscribeOn(Schedulers.io())
                            .delay(1, SECONDS)
                            .subscribe { it ->
                                putInfoToPrefs(it)
                                subject.onNext(it)
                                initializeData.execute(it)
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe ({
                                    completeSubject.onNext(true)
                                }, { Timber.e(it, "LOG EERRORS BIATCH")})
                            }.addTo(compositeDisposable)
                }.addTo(compositeDisposable)
    }

    private fun reinitApplication(isDuringFlow: Boolean) {
        val name: String = if (isDuringFlow) {
            getClubInfoFromPrefs.getClubName()
        } else {
            getFavouriteTeam.getClubName()
        }
        getClubInfoByName.execute(name)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .delay(1, SECONDS)
                .subscribe { it ->
                    putInfoToPrefs(it)
                    subject.onNext(it)
                    initializeData.execute(it)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe({
                                completeSubject.onNext(true)
                            }, {Timber.e(it, "LOG EERRORS BIATCH")})
                }.addTo(compositeDisposable)
    }

    private fun putInfoToPrefs(clubInfo: ClubInfo) {
        preferences.putPreferenceString(KEY_CLUB_NAME, clubInfo.name)
        preferences.putPreferenceInt(KEY_CLUB_ID, clubInfo.matchTeamId)
    }
}