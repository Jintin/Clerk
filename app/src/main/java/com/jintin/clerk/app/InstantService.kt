package com.jintin.clerk.app

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.content.res.Configuration
import android.graphics.PixelFormat
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.O
import android.view.Gravity
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.view.WindowManager
import android.view.WindowManager.LayoutParams.*
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.Observer
import com.jintin.clerk.app.dagger.component.ViewerComponent
import com.jintin.clerk.app.obj.ClerkLog
import com.jintin.clerk.app.utils.PrefKey
import com.jintin.clerk.app.utils.getSystemManager
import com.jintin.clerk.app.utils.hasOverlayPermission
import com.jintin.clerk.app.view.BubbleView
import com.jintin.clerk.app.view.InstantLayout
import com.jintin.clerk.app.viewmodel.LogListViewModel
import javax.inject.Inject

/**
 * Instant log service
 */
class InstantService : LifecycleService() {

    @Inject
    lateinit var viewModel: LogListViewModel
    lateinit var para: WindowManager.LayoutParams
    private lateinit var windowManager: WindowManager
    private lateinit var component: ViewerComponent
    private var container: InstantLayout? = null

    private var offsetX = 0
    private var offsetY = 0

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
            container = createInstantLayout()
            para = getLayoutParams(true)
            windowManager.addView(container, para)
        } else {
            updateSize(container?.isMinimize() == true)
            windowManager.updateViewLayout(container, para)
        }
    }

    private fun createInstantLayout(): InstantLayout {
        return InstantLayout(applicationContext).also {
            it.setBubbleActionListener(object : BubbleView.OnBubbleActionListener {
                override fun onBubbleDragStart(x: Int, y: Int) {
                    offsetX = x - para.x
                    offsetY = y - para.y
                }

                override fun onBubbleDragEnd() {
                }

                override fun onBubbleMinimize(minimize: Boolean) {
                    updateSize(minimize)
                    windowManager.updateViewLayout(container, para)
                }

                override fun onBubbleMove(x: Int, y: Int) {
                    updateOffset(x - offsetX, y - offsetY)
                    windowManager.updateViewLayout(container, para)
                }
            })
        }
    }

    private fun removeOverlay() {
        container?.let {
            windowManager.removeView(it)
            container = null
        }
    }

    private fun updateOffset(x: Int, y: Int) {
        para.x = x
        para.y = y
    }

    private fun updateSize(minimize: Boolean) {
        val size = if (minimize) WRAP_CONTENT else MATCH_PARENT
        para.width = size
        para.height = size
        para.flags = if (minimize) FLAG_NOT_FOCUSABLE else FLAG_WATCH_OUTSIDE_TOUCH
    }

    private fun getLayoutParams(minimize: Boolean): WindowManager.LayoutParams {
        val type = if (SDK_INT >= O) {
            TYPE_APPLICATION_OVERLAY
        } else {
            @Suppress("DEPRECATION")
            TYPE_PHONE
        }
        val size = if (minimize) WRAP_CONTENT else MATCH_PARENT
        val flags = if (container?.isMinimize() == true) FLAG_NOT_FOCUSABLE else FLAG_WATCH_OUTSIDE_TOUCH

        val para = WindowManager.LayoutParams(size, size, type, flags, PixelFormat.TRANSLUCENT)

        para.gravity = Gravity.TOP or Gravity.START
        return para
    }

    private fun createNotificationChannel() {
        if (SDK_INT >= O) {
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
}