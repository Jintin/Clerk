package com.jintin.clerk.app.utils

import androidx.fragment.app.Fragment

/**
 * Check has overlay permission
 */
fun Fragment.hasOverlayPermission(): Boolean {
    return context?.hasOverlayPermission() ?: false
}

/**
 * Get boolean from Preference
 */
fun Fragment.getBool(key: String): Boolean {
    return context?.getBool(key) ?: false
}