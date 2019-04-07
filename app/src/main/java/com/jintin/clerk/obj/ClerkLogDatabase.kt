package com.jintin.clerk.obj

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ClerkLog::class], version = 1, exportSchema = false)
abstract class ClerkLogDatabase : RoomDatabase() {

    abstract fun clerkLogDao(): ClerkLogDao

}