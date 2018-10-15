package szeptunm.corner.ui.splashScreen

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import szeptunm.corner.commons.Constants.KEY_CLUB_NAME
import szeptunm.corner.commons.Preferences
import szeptunm.corner.domain.splashScreen.GetClubInfoByName
import szeptunm.corner.domain.splashScreen.InitializeClubInfo
import szeptunm.corner.domain.splashScreen.InitializeData
import szeptunm.corner.entity.ClubInfo
import java.util.concurrent.TimeUnit.SECONDS
import javax.inject.Inject

class SplashScreenViewModel @Inject constructor(private val initializeClubInfo: InitializeClubInfo,
        private val initializeData: InitializeData, private val getClubInfoByName: GetClubInfoByName,
        private val preferences: Preferences,
        private val getClubInfoFromPrefs: GetClubInfoFromPrefs) {

    private var subject: BehaviorSubject<ClubInfo> = BehaviorSubject.create()
    private var completeSubject: BehaviorSubject<Boolean> = BehaviorSubject.create()
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun observeCompletable(): Observable<Boolean> = completeSubject
    fun observeClubInfo(): Observable<ClubInfo> = subject

    fun init(clubName: String?) {
        initializeClubInfo.execute()
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe {
                    getClubInfoByName.execute(getClubName(clubName))
                            .observeOn(Schedulers.io())
                            .subscribeOn(Schedulers.io())
                            .delay(1, SECONDS)
                            .subscribe { it ->
                                putNameToPrefs(it.name)
                                subject.onNext(it)
                                initializeData.execute(it).subscribe {
                                    completeSubject.onNext(true)
                                }
                            }.addTo(compositeDisposable)
                }.addTo(compositeDisposable)
    }

    private fun getClubName(clubName: String?): String {
        return clubName ?: "FC Barcelona"
    }

    private fun putNameToPrefs(clubName: String) {
        preferences.putPreferenceString(KEY_CLUB_NAME, clubName)
    }
}