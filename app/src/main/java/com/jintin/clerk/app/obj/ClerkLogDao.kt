package com.jintin.clerk.app.obj

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

/**
 * ClerkLogDao for data query
 */
@Dao
interface ClerkLogDao {

    /**
     * Get LiveData of logs
     */
    @Query("SELECT * FROM logs")
    fun getLogs(): LiveData<List<ClerkLog>>

    /**
     * Get LiveData of logs from specific app
     */
    @Query("SELECT * FROM logs where app = :app")
    fun getLogs(app: String): LiveData<List<ClerkLog>>

    /**
     * Insert new logs
     */
    @Insert
    fun insert(vararg users: ClerkLog)

    /**
     * Clear all logs
     */
    @Query("DELETE FROM logs")
    fun clearLogs()

}