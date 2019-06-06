package com.jintin.clerk.lib

import android.app.Application
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.util.Log

/**
 * Helper for sending logs
 */
object ClerkUtils {

    private const val CLERK_PACKAGE = "com.jintin.clerk"
    private const val CLERK_CLASS = "$CLERK_PACKAGE.app.LogReceiver"

    const val LOG_ACTION = "com.jintin.clerk.LOG_ACTION"
    const val EXTRA_APP = "app"
    const val EXTRA_CHANNEL = "channel"
    const val EXTRA_DATA = "data"
    const val EXTRA_TYPE = "type"


    private var app: Application? = null

    /**
     * Init the context
     */
    fun init(app: Application) {
        this.app = app
    }

    fun v(context: Context? = null, channel: String? = null, text: String) {
        log(context, channel, text, LogType.VERBOSE)
    }

    fun d(context: Context? = null, channel: String? = null, text: String) {
        log(context, channel, text, LogType.DEBUG)
    }

    fun i(context: Context? = null, channel: String? = null, text: String) {
        log(context, channel, text, LogType.INFO)
    }

    fun w(context: Context? = null, channel: String? = null, text: String) {
        log(context, channel, text, LogType.WARN)
    }

    fun e(context: Context? = null, channel: String? = null, text: String) {
        log(context, channel, text, LogType.ERROR)
    }

    /**
     * Log method
     */
    fun log(context: Context? = null, channel: String?, text: String, type: LogType) {
        (app ?: context)?.let { ctx ->
            Intent(LOG_ACTION).run {
                putExtra(EXTRA_DATA, text)
                channel?.let { channel ->
                    putExtra(EXTRA_CHANNEL, channel)
                }
                putExtra(EXTRA_APP, ctx.packageName)
                putExtra(EXTRA_TYPE, type.ordinal)
                component = ComponentName(CLERK_PACKAGE, CLERK_CLASS)
                ctx.sendBroadcast(this)
            }
        } ?: run {
            Log.e("Clerk", "Context is not initialized")
        }
    }
}