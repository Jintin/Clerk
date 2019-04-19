package com.jintin.clerk.app.repository

import androidx.lifecycle.LiveData
import com.jintin.clerk.app.obj.ClerkLog

/**
 * LogRepository interface
 */
interface LogRepository {
    /**
     * Insert new log
     */
    fun addLog(log: ClerkLog)

    /**
     * Get LiveData of logs
     */
    fun getLogList(): LiveData<List<ClerkLog>>

    /**
     * Get LiveData of logs from specific app
     */
    fun getLogList(app: String): LiveData<List<ClerkLog>>

    /**
     * Clear all logs
     */
    fun clearLogs()

}