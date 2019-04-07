package com.jintin.clerk.repository

import androidx.lifecycle.LiveData
import com.jintin.clerk.obj.ClerkLog
import com.jintin.clerk.obj.ClerkLogDao
import io.reactivex.Maybe
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

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