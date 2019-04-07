package com.jintin.clerk.app

import android.app.IntentService
import android.content.Intent
import com.jintin.clerk.app.dagger.component.ServiceComponent
import com.jintin.clerk.app.obj.ClerkLog
import com.jintin.clerk.app.repository.LogRepository
import javax.inject.Inject

class LogService : IntentService("log") {

    companion object {
        const val CLERK_LOG = "log"
    }

    @Inject
    lateinit var logRepository: LogRepository

    override fun onCreate() {
        super.onCreate()
        ServiceComponent.init().inject(this)
    }

    override fun onHandleIntent(intent: Intent?) {
        intent?.getParcelableExtra<ClerkLog>(CLERK_LOG)?.let {
            logRepository.addLog(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        ServiceComponent.clear()
    }

}