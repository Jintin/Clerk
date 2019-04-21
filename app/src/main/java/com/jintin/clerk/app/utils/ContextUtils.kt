package com.jintin.clerk.app.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import androidx.preference.PreferenceManager
import kotlin.reflect.KClass

/**
 * Check has overlay permission
 */
fun Context.hasOverlayPermission(): Boolean {
    return Build.VERSION.SDK_INT < Build.VERSION_CODES.M || Settings.canDrawOverlays(this)
}

/**
 * Get boolean from Preference
 */
fun Context.getBool(key: String): Boolean {
    return PreferenceManager.getDefaultSharedPreferences(this)
        .getBoolean(key, false)
}

/**
 * Wrapper of getSystemService
 */
fun <T> Context.getSystemManager(name: String): T {
    @Suppress("UNCHECKED_CAST")
    return getSystemService(name) as T
}

/**
 * Wrapper of startActivity
 */
fun Context.startActivity(cls: KClass<out Activity>) {
    startActivity(Intent(this, cls.java))
}