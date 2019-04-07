package com.jintin.clerk.repository

import androidx.lifecycle.LiveData
import com.jintin.clerk.obj.ClerkLog

interface LogRepository {

    fun addLog(log: ClerkLog)

    fun getLogList(): LiveData<List<ClerkLog>>

    fun getLogList(app: String): LiveData<List<ClerkLog>>

    fun clearLogs()
}