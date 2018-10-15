package szeptunm.corner.ui.splashScreen

import android.content.Intent
import android.os.Bundle
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import szeptunm.corner.R
import szeptunm.corner.R.layout
import szeptunm.corner.commons.Constants.KEY_CLUB_INFO
import szeptunm.corner.commons.Constants.KEY_CLUB_NAME
import szeptunm.corner.ui.BaseActivity
import szeptunm.corner.ui.MainActivity
import javax.inject.Inject

class SplashScreenActivity @Inject constructor() : BaseActivity() {


    override val layoutResource: Int
        get() = R.layout.activity_splash_screen

    val clubInfo: String?
        get() = intent.getStringExtra(KEY_CLUB_NAME)

    private var composite: CompositeDisposable = CompositeDisposable()

    @Inject
    lateinit var viewModel: SplashScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_splash_screen)
        subscribeToViewModel()
    }

    private fun subscribeToViewModel() {
        viewModel.init(clubInfo)
        val intent = Intent(this, MainActivity::class.java)
        viewModel.observeClubInfo().subscribe {
            val bundle = Bundle().apply {
                putParcelable(KEY_CLUB_INFO, it)
            }
            intent.putExtras(bundle)
        }.addTo(composite)

        viewModel.observeCompletable().subscribe {
            if (it == true) {
                startActivity(intent)
                finish()
            }
        }.addTo(composite)
    }
}