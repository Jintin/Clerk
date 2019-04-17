package com.jintin.clerk

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.jintin.clerk.app.obj.ClerkLog
import com.jintin.clerk.app.obj.ClerkLogDao
import com.jintin.clerk.app.repository.LogRepository
import com.jintin.clerk.app.repository.LogRepositoryImpl
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

/**
 * Test LogRepository
 */
@RunWith(MockitoJUnitRunner::class)
class LogRepositoryTest {

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var localDataSource: ClerkLogDao
    private lateinit var logRepository: LogRepository

    /**
     * Initial setup
     */
    @Before
    fun setup() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        logRepository = LogRepositoryImpl(localDataSource)
    }

    /**
     * Teardown
     */
    @After
    fun teardown() {
        RxJavaPlugins.setIoSchedulerHandler { null }
    }

    @Test
    fun getEmptyLog() {
        val data = MutableLiveData<List<ClerkLog>>()
        data.postValue(listOf())
        `when`(localDataSource.getLogs()).thenReturn(data)
        assertEquals(0, logRepository.getLogList().value?.size)
    }

    @Test
    fun getLogs() {
        val SIZE = 4
        val data = MutableLiveData<List<ClerkLog>>()
        data.postValue(getLogList(SIZE))
        `when`(localDataSource.getLogs()).thenReturn(data)

        assertEquals(SIZE, logRepository.getLogList().value?.size)
    }

    @Test
    fun addLog() {
        val log = getLog()
        logRepository.addLog(log)
        verify(localDataSource).insert(log)
    }

    @Test
    fun clearLog() {
        logRepository.clearLogs()
        verify(localDataSource).clearLogs()
    }
}
