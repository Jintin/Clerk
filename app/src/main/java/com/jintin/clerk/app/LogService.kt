package com.jintin.clerk.app

import android.content.Context
import android.content.Intent
import androidx.core.app.JobIntentService
import com.jintin.clerk.app.dagger.component.ServiceComponent
import com.jintin.clerk.app.obj.ClerkLog
import com.jintin.clerk.app.repository.LogRepository
import javax.inject.Inject

/**
 * LogService to process incoming logs to database
 */
class LogService : JobIntentService() {

    companion object {

        const val CLERK_LOG = "log"
        private const val JOB_ID = 1

        /**
         * Enqueue job
         */
        fun enqueueWork(context: Context, work: Intent) {
            enqueueWork(context, LogService::class.java, JOB_ID, work)
        }

    }

    @Inject
    lateinit var logRepository: LogRepository
    private lateinit var component: ServiceComponent

    override fun onCreate() {
        super.onCreate()
        component = ServiceComponent.init()
        component.inject(this)
    }

    override fun onHandleWork(intent: Intent) {
        intent.getParcelableExtra<ClerkLog>(CLERK_LOG)?.let {
            logRepository.addLog(it)
        }
        startService(Intent(this, InstantService::class.java))
    }

}