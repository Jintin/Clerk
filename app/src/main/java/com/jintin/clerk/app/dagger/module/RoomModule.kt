package com.jintin.clerk.app.dagger.module

import android.app.Application
import androidx.room.Room
import com.jintin.clerk.app.obj.ClerkLogDao
import com.jintin.clerk.app.obj.ClerkLogDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule(application: Application) {
    private var database: ClerkLogDatabase =
        Room.databaseBuilder(application, ClerkLogDatabase::class.java, "log-db").build()

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