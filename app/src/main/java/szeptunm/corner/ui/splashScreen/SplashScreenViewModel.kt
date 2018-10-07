package szeptunm.corner.ui.splashScreen

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject
import szeptunm.corner.domain.splashScreen.GetClubInfoByName
import szeptunm.corner.domain.splashScreen.InitializeClubInfo
import szeptunm.corner.domain.splashScreen.InitializeData
import szeptunm.corner.entity.ClubInfo
import javax.inject.Inject

class SplashScreenViewModel @Inject constructor(private var initializeClubInfo: InitializeClubInfo,
        private var initializeData: InitializeData, private var getClubInfoByName: GetClubInfoByName) {

    private var subject: BehaviorSubject<ClubInfo> = BehaviorSubject.create()
    private var completeSubject: BehaviorSubject<Boolean> = BehaviorSubject.create()
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun observeCompletable(): Observable<Boolean> = completeSubject
    fun observeClubInfo(): Observable<ClubInfo> = subject

    fun init() {
        initializeClubInfo.execute()
                .andThen {
                    getClubInfoByName.execute("FC Barcelona")
                            .subscribe { clubInfo -> subject.onNext(clubInfo) }
                }
                .andThen {
                    initializeData.execute(subject.value!!)
                            .doOnComplete {
                                completeSubject.onNext(true)
                            }
                }
    }
}