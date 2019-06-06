package com.jintin.clerk.app.obj

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * ClerkLogDatabase for RoomDatabase
 */
@Database(entities = [ClerkLog::class], version = 2, exportSchema = false)
abstract class ClerkLogDatabase : RoomDatabase() {

    /**
     * Bind ClerkLogDao
     */
    abstract fun clerkLogDao(): ClerkLogDao

}