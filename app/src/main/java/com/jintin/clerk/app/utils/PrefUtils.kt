package com.jintin.clerk.app.utils

import android.app.Activity
import androidx.preference.PreferenceManager

/**
 * Get boolean from Preference
 */
fun Activity?.getBool(key: String): Boolean {
    if (this == null) {
        return false
    }
    return PreferenceManager.getDefaultSharedPreferences(this)
        .getBoolean(key, false)
}