package com.jintin.clerk.app.utils

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager

/**
 * Get boolean from Preference
 */
fun Context?.getBool(key: String): Boolean {
    if (this == null) {
        return false
    }
    return PreferenceManager.getDefaultSharedPreferences(this)
        .getBoolean(key, false)
}

/**
 * Set boolean from Preference
 */
fun Context?.setBool(key: String, value: Boolean) {
    if (this == null) {
        return
    }
    PreferenceManager.getDefaultSharedPreferences(this)
        .edit().putBoolean(key, value).apply()
}

/**
 * Get boolean from Preference
 */
fun Fragment.getBool(key: String): Boolean {
    return activity?.getBool(key) ?: false
}

/**
 * Set boolean from Preference
 */
fun Fragment.setBool(key: String, value: Boolean) {
    activity?.setBool(key, value)
}