package com.jintin.clerk.app

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.jintin.clerk.lib.ClerkUtils
import com.jintin.clerk.obj.ClerkLog

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
                context?.startService(
                    Intent(context, LogService::class.java)
                        .putExtra(
                            LogService.CLERK_LOG,
                            ClerkLog(
                                app = app, channel = channel, log = string
                            )
                        )
                )
            }
        }

    }
}