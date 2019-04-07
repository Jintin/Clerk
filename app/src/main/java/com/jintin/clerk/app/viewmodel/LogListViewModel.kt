package com.jintin.clerk.app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jintin.clerk.app.obj.ClerkLog
import com.jintin.clerk.app.repository.LogRepository

class LogListViewModel(val logRepository: LogRepository) : ViewModel() {

    fun getList(): LiveData<List<ClerkLog>> {
        return logRepository.getLogList()
    }

    fun clear() {
        logRepository.clearLogs()
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(private val logRepository: LogRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return LogListViewModel(logRepository) as T
        }
    }
}