package com.jintin.clerk

import com.jintin.clerk.app.obj.ClerkLog

fun getLogList(size: Int): List<ClerkLog> {
    val list = mutableListOf<ClerkLog>()

    for (i in 1..size) {
        list.add(ClerkLog(0, "", "", ""))
    }
    return list
}