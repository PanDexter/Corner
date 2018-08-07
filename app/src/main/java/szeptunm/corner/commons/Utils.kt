package szeptunm.corner.commons

import android.content.Context
import android.net.ConnectivityManager
import javax.inject.Inject

class Utils @Inject constructor(private val context: Context) {

    fun isConnectedToInternet(): Boolean {
        val connectivity = context.getSystemService(
                Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = connectivity.activeNetworkInfo
        if (info.isConnected) {
            return true
        }
        return false
    }
}