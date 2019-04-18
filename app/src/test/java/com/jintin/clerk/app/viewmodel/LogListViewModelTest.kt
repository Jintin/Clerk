package com.jintin.clerk.app.viewmodel

import com.jintin.clerk.app.repository.LogRepository
import com.jintin.clerk.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * Test LogRepository
 */
@RunWith(MockitoJUnitRunner::class)
class LogListViewModelTest {

    @Mock
    private lateinit var logRepository: LogRepository
    private lateinit var logListViewModel: LogListViewModel

    /**
     * Initial setup
     */
    @Before
    fun setup() {
        logListViewModel = LogListViewModel(logRepository)
    }

    /**
     * Test get log list
     */
    @Test
    fun getList() {
        logListViewModel.getList()
        verify(logRepository).getLogList()
    }

    /**
     * Test clear log
     */
    @Test
    fun clear() {
        logListViewModel.clear()
        verify(logRepository).clearLogs()
    }
}