package com.jintin.clerk.app.utils

import android.content.Context

/**
 * wrapper of getSystemService
 */
fun <T> Context.getSystemManager(name: String): T {
    @Suppress("UNCHECKED_CAST")
    return getSystemService(name) as T
}