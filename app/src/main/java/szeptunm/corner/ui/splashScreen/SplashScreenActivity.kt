package szeptunm.corner.ui.splashScreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.databinding.DataBindingUtil
import com.google.android.material.snackbar.Snackbar
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import szeptunm.corner.R
import szeptunm.corner.R.layout
import szeptunm.corner.commons.Constants.IS_DURING_FLOW
import szeptunm.corner.commons.Constants.KEY_CLUB_FAVOURITE
import szeptunm.corner.commons.Constants.KEY_CLUB_NAME
import szeptunm.corner.commons.Preferences
import szeptunm.corner.databinding.ActivitySplashScreenBinding
import szeptunm.corner.ui.BaseActivity
import szeptunm.corner.ui.MainActivity
import javax.inject.Inject

class SplashScreenActivity @Inject constructor() : BaseActivity() {


    override val layoutResource: Int
        get() = R.layout.activity_splash_screen

    private val isDuringFlow: Boolean
        get() = intent.getBooleanExtra(IS_DURING_FLOW, false)

    private var composite: CompositeDisposable = CompositeDisposable()

    private lateinit var binding: ActivitySplashScreenBinding

    @Inject
    lateinit var preferences: Preferences

    val favouriteClub: String
        get() = intent.getStringExtra(KEY_CLUB_FAVOURITE) ?: preferences.getPreferenceString(KEY_CLUB_FAVOURITE,
                "").toString()

    @Inject
    lateinit var viewModel: SplashScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen)
        setContentView(layout.activity_splash_screen)
        if (!viewModel.isOnline()) {
            showSnackbar("There is no internet connection, app will be closed")
        } else {
            subscribeToViewModel()
        }
    }

    private fun subscribeToViewModel() {
        viewModel.init(isDuringFlow)
        val intent = Intent(this, MainActivity::class.java)
        viewModel.observeClubInfo()
                .doOnError {
                    showSnackbar("There is problem with API, try again later")
                }
                .subscribe {
                    val bundle = Bundle().apply {
                        putParcelable(KEY_CLUB_NAME, it)
                    }
                    intent.putExtras(bundle)
                }.addTo(composite)

        viewModel.observeCompletable()
                .doOnError {
                    showSnackbar("There is problem with API, try again later")
                }
                .subscribe {
                    if (it == true) {
                        startActivity(intent)
                        finish()
                    }
                }.addTo(composite)
    }

    private fun showSnackbar(text: String) {
        Snackbar.make(this.findViewById(R.id.splash_view),
                text,
                Snackbar.LENGTH_LONG).show().apply {
            val handler = Handler()
            handler.postDelayed({
                finish()
            }, 3000)
        }
    }
}