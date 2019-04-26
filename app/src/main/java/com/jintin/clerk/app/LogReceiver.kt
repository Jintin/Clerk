package com.jintin.clerk.app

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.jintin.clerk.app.obj.ClerkLog
import com.jintin.clerk.app.utils.PrefKey
import com.jintin.clerk.app.utils.getBool
import com.jintin.clerk.app.utils.hasOverlayPermission
import com.jintin.clerk.app.utils.startForegroundService
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
                val clerkLog = ClerkLog(app = app, channel = channel, log = string)
                context?.apply {
                    if (hasOverlayPermission() && getBool(PrefKey.DRAW_OVERLAY)) {
                        startInstantService(this)
                    }
                    startLogService(this, clerkLog)
                }
            }
        }
    }

    private fun startInstantService(context: Context) {
        context.startForegroundService(InstantService::class)
    }

    private fun startLogService(context: Context, log: ClerkLog) {
        val serviceIntent = Intent(context, LogService::class.java)
            .putExtra(LogService.CLERK_LOG, log)
        LogService.enqueueWork(context, serviceIntent)
    }

}