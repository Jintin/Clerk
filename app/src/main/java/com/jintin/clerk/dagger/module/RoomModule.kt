package com.jintin.clerk.dagger.module

import android.app.Application
import androidx.room.Room
import com.jintin.clerk.obj.ClerkLogDao
import com.jintin.clerk.obj.ClerkLogDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule(application: Application) {
    private var database: ClerkLogDatabase

    init {
        database = Room.databaseBuilder(application, ClerkLogDatabase::class.java, "log-db").build()
    }

    @Singleton
    @Provides
    fun providesRoomDatabase(): ClerkLogDatabase {
        return database
    }

    @Singleton
    @Provides
    fun providesClerkLogDao(database: ClerkLogDatabase): ClerkLogDao {
        return database.clerkLogDao()
    }
}