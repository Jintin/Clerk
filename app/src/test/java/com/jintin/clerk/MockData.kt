package com.jintin.clerk

import com.jintin.clerk.app.obj.ClerkLog

fun getLog(): ClerkLog {
    return ClerkLog(0, "", "", "")
}

fun getLogList(size: Int): List<ClerkLog> {
    val list = mutableListOf<ClerkLog>()

    for (i in 1..size) {
        list.add(getLog())
    }
    return list
}