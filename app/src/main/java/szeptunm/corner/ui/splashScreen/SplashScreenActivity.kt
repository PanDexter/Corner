package szeptunm.corner.ui.splashScreen

import szeptunm.corner.R
import szeptunm.corner.ui.BaseActivity
import javax.inject.Inject

class SplashScreenActivity @Inject constructor() : BaseActivity() {

    override val layoutResource: Int
        get() = R.layout.activity_splash_screen
}