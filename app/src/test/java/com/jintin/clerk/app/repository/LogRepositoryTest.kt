package com.jintin.clerk.app.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.jintin.clerk.app.obj.ClerkLog
import com.jintin.clerk.app.obj.ClerkLogDao
import com.jintin.clerk.getLog
import com.jintin.clerk.getLogList
import com.jintin.clerk.verify
import com.jintin.clerk.whenever
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.*
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
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

    /**
     * Test get empty log
     */
    @Test
    fun getEmptyLog() {
        val data = MutableLiveData<List<ClerkLog>>()
        data.postValue(listOf())
        whenever(localDataSource.getLogs()).thenReturn(data)
        Assert.assertEquals(0, logRepository.getLogList().value?.size)
    }

    /**
     * Test get log
     */
    @Test
    fun getLogs() {
        val size = 4
        val data = MutableLiveData<List<ClerkLog>>()
        data.postValue(getLogList(size))
        whenever(localDataSource.getLogs()).thenReturn(data)
        Assert.assertEquals(size, logRepository.getLogList().value?.size)
    }

    /**
     * Test add log
     */
    @Test
    fun addLog() {
        val log = getLog()
        logRepository.addLog(log)
        verify(localDataSource).insert(log)
    }

    /**
     * Test clear log
     */
    @Test
    fun clearLog() {
        logRepository.clearLogs()
        verify(localDataSource).clearLogs()
    }
}
