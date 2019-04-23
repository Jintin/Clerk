package com.jintin.clerk.lib

import android.content.ComponentName
import android.content.Context
import android.content.Intent

/**
 * Helper for sending logs
 */
object ClerkUtils {

    const val LOG_ACTION = "com.jintin.clerk.LOG_ACTION"
    private const val CLERK_PACKAGE = "com.jintin.clerk"
    private const val CLERK_CLASS = "$CLERK_PACKAGE.app.LogReceiver"
    const val EXTRA_APP = "app"
    const val EXTRA_CHANNEL = "channel"
    const val EXTRA_DATA = "data"

    /**
     * Log method without channel
     */
    fun log(context: Context, text: String) {
        log(context, null, text)
    }

    /**
     * Log method
     */
    fun log(context: Context, channel: String?, text: String) {
        Intent(LOG_ACTION).also {
            it.putExtra(EXTRA_DATA, text)
            channel?.let { channel ->
                it.putExtra(EXTRA_CHANNEL, channel)
            }
            it.putExtra(EXTRA_APP, context.packageName)
            it.component = ComponentName(CLERK_PACKAGE, CLERK_CLASS)
            context.sendBroadcast(it)
        }
    }
}