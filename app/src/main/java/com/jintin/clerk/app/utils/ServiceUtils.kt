package com.jintin.clerk.app.utils

import android.content.Context

fun <T> Context.getSystemManager(name: String): T {
    @Suppress("UNCHECKED_CAST")
    return getSystemService(name) as T
}