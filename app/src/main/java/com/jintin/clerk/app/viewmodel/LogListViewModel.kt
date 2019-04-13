package com.jintin.clerk.app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jintin.clerk.app.obj.ClerkLog
import com.jintin.clerk.app.repository.LogRepository

/**
 * ViewModel for LogListFragment
 */
class LogListViewModel(private val logRepository: LogRepository) : ViewModel() {

    /**
     * Get LiveData of log list
     */
    fun getList(): LiveData<List<ClerkLog>> {
        return logRepository.getLogList()
    }

    /**
     * Clear all logs
     */
    fun clear() {
        logRepository.clearLogs()
    }

    /**
     * Factory for instantiate LogListViewModel
     */
    @Suppress("UNCHECKED_CAST")
    class Factory(private val logRepository: LogRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return LogListViewModel(logRepository) as T
        }
    }
}