package szeptunm.corner.commons

import android.content.SharedPreferences

class Preferences(private val preferences: SharedPreferences) {

    fun getPreferenceBoolean(key: String, defValue: Boolean): Boolean {
        return preferences.getBoolean(key, defValue)
    }

    fun getPreferenceInt(key: String, defValue: Int): Int {
        return preferences.getInt(key, defValue)
    }

    fun getPreferenceLong(key: String, defValue: Long): Long {
        return preferences.getLong(key, defValue)
    }

    fun getPreferenceFloat(key: String, defValue: Float): Float {
        return preferences.getFloat(key, defValue)
    }

    fun getPreferenceString(key: String, defValue: String): String? {
        return preferences.getString(key, defValue)
    }

    fun putPreferenceBoolean(key: String, value: Boolean) {
        preferences.edit()
                .putBoolean(key, value)
                .apply()
    }

    fun putPreferenceInt(key: String, value: Int) {
        preferences.edit()
                .putInt(key, value)
                .apply()
    }

    fun putPreferenceLong(key: String, value: Long) {
        preferences.edit()
                .putLong(key, value)
                .apply()
    }

    fun putPreferenceFloat(key: String, value: Float) {
        preferences.edit()
                .putFloat(key, value)
                .apply()
    }

    fun putPreferenceString(key: String, value: String) {
        preferences.edit()
                .putString(key, value)
                .apply()
    }

    fun removePreference(key: String) {
        preferences.edit()
                .remove(key)
                .apply()
    }

    companion object {
        const val PREFERENCES_FILE_NAME = "sharedPreferencesCorner"
    }
}