package com.jintin.clerk.app.repository

import androidx.lifecycle.LiveData
import com.jintin.clerk.app.obj.ClerkLog
import com.jintin.clerk.app.obj.ClerkLogDao
import io.reactivex.Maybe
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * LogRepository instance
 */
class LogRepositoryImpl @Inject constructor(
    private val localDataSource: ClerkLogDao
) : LogRepository {

    override fun addLog(log: ClerkLog) {
        localDataSource.insert(log)
    }

    override fun getLogList(): LiveData<List<ClerkLog>> {
        return localDataSource.getLogs()
    }

    override fun getLogList(app: String): LiveData<List<ClerkLog>> {
        return localDataSource.getLogs(app)
    }

    override fun clearLogs() {
        Maybe.fromAction<Void> { localDataSource.clearLogs() }
            .subscribeOn(Schedulers.io())
            .subscribe()

    }
}