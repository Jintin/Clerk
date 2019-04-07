package com.jintin.clerk.obj

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ClerkLogDao {
    @Query("SELECT * FROM logs")
    fun getLogs(): LiveData<List<ClerkLog>>

    @Query("SELECT * FROM logs where app = :app")
    fun getLogs(app: String): LiveData<List<ClerkLog>>

    @Insert
    fun insert(vararg users: ClerkLog)

    @Query("DELETE FROM logs")
    fun clearLogs()
}