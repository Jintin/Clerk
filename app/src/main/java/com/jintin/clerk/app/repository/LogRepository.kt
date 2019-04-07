package com.jintin.clerk.app.repository

import androidx.lifecycle.LiveData
import com.jintin.clerk.app.obj.ClerkLog

interface LogRepository {

    fun addLog(log: ClerkLog)

    fun getLogList(): LiveData<List<ClerkLog>>

    fun getLogList(app: String): LiveData<List<ClerkLog>>

    fun clearLogs()
}