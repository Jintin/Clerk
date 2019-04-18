package com.jintin.clerk

import com.jintin.clerk.app.obj.ClerkLog
import org.mockito.Mockito
import org.mockito.stubbing.OngoingStubbing

/**
 * Alias Mockito when
 */
fun <T> whenever(methodCall: T): OngoingStubbing<T> {
    return Mockito.`when`(methodCall)
}

/**
 * Alias Mockito verify
 */
fun <T> verify(mock: T): T {
    return Mockito.verify(mock)
}

/**
 * Get sample ClerkLog
 */
fun getLog(): ClerkLog {
    return ClerkLog(0, "", "", "")
}

/**
 * Get sample ClerkLog List
 */
fun getLogList(size: Int): List<ClerkLog> {
    val list = mutableListOf<ClerkLog>()

    for (i in 1..size) {
        list.add(getLog())
    }
    return list
}