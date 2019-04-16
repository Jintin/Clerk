package com.jintin.clerk.app

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import com.jintin.clerk.app.obj.ClerkLog
import com.jintin.clerk.app.utils.PrefKey
import com.jintin.clerk.app.utils.getBool
import com.jintin.clerk.lib.ClerkUtils

/**
 * LogReceiver to listen incoming logs
 */
class LogReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        when (intent?.action) {
            ClerkUtils.LOG_ACTION -> {
                val app = intent.getStringExtra(ClerkUtils.EXTRA_APP)
                val channel = intent.getStringExtra(ClerkUtils.EXTRA_CHANNEL) ?: ""
                val string = intent.getStringExtra(ClerkUtils.EXTRA_DATA)
                if (app == null || string == null) {
                    return
                }
                context?.apply {
                    if (getBool(PrefKey.DRAW_OVERLAY)) {
                        val serviceIntent = getTargetIntent(InstantService::class.java, this, app, channel, string)
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            startForegroundService(serviceIntent)
                        } else {
                            startService(serviceIntent)
                        }
                    }
                    val serviceIntent = getTargetIntent(LogService::class.java, this, app, channel, string)
                    LogService.enqueueWork(this, serviceIntent)
                }
            }
        }
    }

    private fun getTargetIntent(
        target: Class<out Service>,
        context: Context,
        app: String,
        channel: String,
        string: String
    ) = Intent(context, target)
        .putExtra(
            LogService.CLERK_LOG,
            ClerkLog(app = app, channel = channel, log = string)
        )

}