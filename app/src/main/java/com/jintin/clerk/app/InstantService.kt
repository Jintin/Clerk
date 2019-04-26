package com.jintin.clerk.app

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.content.res.Configuration
import android.graphics.PixelFormat
import android.graphics.Point
import android.os.Build
import android.view.Gravity
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.Observer
import com.jintin.clerk.app.dagger.component.ViewerComponent
import com.jintin.clerk.app.obj.ClerkLog
import com.jintin.clerk.app.utils.PrefKey
import com.jintin.clerk.app.utils.getSystemManager
import com.jintin.clerk.app.utils.hasOverlayPermission
import com.jintin.clerk.app.view.VirtualView
import com.jintin.clerk.app.viewmodel.LogListViewModel
import javax.inject.Inject

/**
 * Instant log service
 */
class InstantService : LifecycleService() {

    @Inject
    lateinit var viewModel: LogListViewModel
    private lateinit var windowManager: WindowManager
    private lateinit var component: ViewerComponent
    private var container: VirtualView? = null

    override fun onCreate() {
        super.onCreate()
        windowManager = getSystemManager(WINDOW_SERVICE)
        component = ViewerComponent.init()
        component.inject(this)
        viewModel.getList().observe(this, Observer<List<ClerkLog>> {
            container?.setData(it)
        })
        createOverlay()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        createOverlay()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        removeOverlay()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        createOverlay()
    }

    private fun createOverlay() {
        if (!hasOverlayPermission()) {
            return
        }
        setNotification()
        if (container == null) {
            container = VirtualView(applicationContext)
            container?.setOnBackListener(object : VirtualView.OnBackListener {
                override fun onBackClick() {
                    stopSelf()
                }
            })
            windowManager.addView(container, getOverlayLayoutParams())
        } else {
            windowManager.updateViewLayout(container, getOverlayLayoutParams())
        }
    }

    private fun getOverlayLayoutParams(): ViewGroup.LayoutParams {
        val type = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
        } else {
            @Suppress("DEPRECATION")
            WindowManager.LayoutParams.TYPE_PHONE
        }
        val size = Point()
        windowManager.defaultDisplay.getSize(size)
        return WindowManager.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT,
            type,
            WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM,
            PixelFormat.TRANSLUCENT
        ).apply {
            gravity = Gravity.BOTTOM
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager = getSystemService(NotificationManager::class.java)
            if (notificationManager.getNotificationChannel(PrefKey.DRAW_OVERLAY) == null) {
                val notificationChannel =
                    NotificationChannel(
                        PrefKey.DRAW_OVERLAY,
                        getString(R.string.instant_log),
                        NotificationManager.IMPORTANCE_LOW
                    )
                notificationChannel.description = getString(R.string.instant_log)
                notificationManager.createNotificationChannel(notificationChannel)
            }
        }
    }

    private fun setNotification() {
        createNotificationChannel()
        val intent = Intent(this, InstantService::class.java)
        val pendingIntent = PendingIntent.getService(this, 0, intent, 0)

        val notification = NotificationCompat.Builder(this, PrefKey.DRAW_OVERLAY)
            .setWhen(0)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(getString(R.string.instant_log))
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setContentIntent(pendingIntent).build()
        startForeground(1, notification)
    }

    private fun removeOverlay() {
        container?.let {
            windowManager.removeView(it)
            container = null
        }
    }
}