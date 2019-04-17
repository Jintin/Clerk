package com.jintin.clerk

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.jintin.clerk.app.obj.ClerkLog
import com.jintin.clerk.app.obj.ClerkLogDao
import com.jintin.clerk.app.repository.LogRepository
import com.jintin.clerk.app.repository.LogRepositoryImpl
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class LogRepositoryTest {
    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var localDataSource: ClerkLogDao
    private lateinit var logRepository: LogRepository

    @Before
    fun setup() {
        logRepository = LogRepositoryImpl(localDataSource)
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
}
