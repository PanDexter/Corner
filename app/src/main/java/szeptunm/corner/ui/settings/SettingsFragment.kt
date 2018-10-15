package szeptunm.corner.ui.settings

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import szeptunm.corner.R
import szeptunm.corner.commons.Constants.KEY_CLUB_NAME
import szeptunm.corner.databinding.FragmentSettingsBinding
import szeptunm.corner.entity.ClubInfo
import szeptunm.corner.ui.BaseFragment
import szeptunm.corner.ui.splashScreen.SplashScreenActivity
import javax.inject.Inject

class SettingsFragment : BaseFragment() {

    override val layoutResource: Int
        get() = R.layout.fragment_settings

    @Inject
    lateinit var viewModel: SettingsViewModel

    @Inject
    lateinit var clubInfo: ClubInfo

//    @Inject
//    lateinit var preferences: Preferences


    private lateinit var binding: FragmentSettingsBinding

    companion object {
        fun newInstance(): SettingsFragment = SettingsFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = viewDataBinding as FragmentSettingsBinding
//        binding.clubButton = clubInfo.name
        binding.clubButton.setOnClickListener {

            val popUp = PopupMenu(context, it, 0)
            popUp.menuInflater.inflate(R.menu.popup_menu, popUp.menu)

            popUp.setOnMenuItemClickListener { menuItem ->
                // viewModel.setNewClubInfo(menuItem.title.toString())
                restartApp(menuItem.title.toString())
                true
            }
            popUp.show()
        }
    }

    private fun restartApp(clubName: String) {
        val intent = Intent(activity, SplashScreenActivity::class.java)
        val bundle = Bundle().apply {
            putString(KEY_CLUB_NAME, clubName)
        }
        //preferences.putPreferenceString(KEY_CLUB_NAME, clubName)
        intent.putExtras(bundle)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        activity?.finish()
        startActivity(intent)
    }
}