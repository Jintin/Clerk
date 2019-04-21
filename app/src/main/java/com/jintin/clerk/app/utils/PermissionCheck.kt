package com.jintin.clerk.app.utils

import android.content.Context
import android.os.Build
import android.provider.Settings
import androidx.fragment.app.Fragment

/**
 * Check has overlay permission
 */
fun Context.hasOverlayPermission(): Boolean {
    return Build.VERSION.SDK_INT < Build.VERSION_CODES.M || Settings.canDrawOverlays(this)
}

/**
 * Check has overlay permission
 */
fun Fragment.hasOverlayPermission(): Boolean {
    return activity?.hasOverlayPermission() ?: false
}