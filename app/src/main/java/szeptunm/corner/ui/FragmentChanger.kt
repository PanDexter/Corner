package szeptunm.corner.ui

import android.util.Log
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import javax.inject.Inject

class FragmentChanger @Inject constructor() {

    fun changeFragments(activity: MainActivity, @IdRes containerId: Int, newFragmentTag: String,
            fragmentCreator: () -> Fragment): Fragment? {
        val manager = activity.supportFragmentManager
        val transaction = manager.beginTransaction()
        var fragment = handleCurrentFragment(manager, transaction, newFragmentTag, containerId)
        if (fragment == null) {
            fragment = handleNewFragment(manager, transaction, newFragmentTag, containerId, fragmentCreator)
        }
        transaction.commit()
        return fragment
    }

    private fun handleCurrentFragment(manager: FragmentManager, transaction: FragmentTransaction,
            fragmentTag: String, @IdRes containerId: Int): Fragment? {
        val findFragmentById = manager.findFragmentById(containerId)
        if (findFragmentById != null) {
            Log.i("TAG", "TAGS: fragmenttag: ${findFragmentById.tag}   passedTag: $fragmentTag")
            if (findFragmentById.tag == fragmentTag) {
                if (findFragmentById.isDetached) {
                    transaction.attach(findFragmentById)
                }
                return findFragmentById
            }
            Log.i("TAG", "Detach fragment: ${findFragmentById.tag}")
            transaction.detach(findFragmentById)
        }
        return null
    }

    private fun handleNewFragment(manager: FragmentManager, transaction: FragmentTransaction,
            fragmentTag: String, @IdRes containerId: Int, fragmentCreator: () -> Fragment): Fragment? {
        var findFragmentByTag = manager.findFragmentByTag(fragmentTag)
        if (findFragmentByTag == null) {
            findFragmentByTag = fragmentCreator()
            transaction.addToBackStack(null).add(containerId, findFragmentByTag, fragmentTag)
            Log.i("TAG", "Add new fragment: $fragmentTag")
        } else {
            transaction.attach(findFragmentByTag)
            Log.i("TAG", "Attach fragment: ${findFragmentByTag.tag}")
        }
        return findFragmentByTag
    }
}