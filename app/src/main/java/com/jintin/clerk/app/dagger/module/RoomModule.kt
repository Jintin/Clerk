package com.jintin.clerk.app.dagger.module

import android.app.Application
import androidx.room.Room
import com.jintin.clerk.app.obj.ClerkLogDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * RoomModule for DataBase classes
 */
@Module
class RoomModule(application: Application) {

    private var database: ClerkLogDatabase =
        Room.databaseBuilder(application, ClerkLogDatabase::class.java, "log-db").build()

    /**
     * Provide ClerkLogDatabase
     */
    @Singleton
    @Provides
    fun providesRoomDatabase() = database

    /**
     * Provide ClerkLogDao
     */
    @Singleton
    @Provides
    fun providesClerkLogDao(database: ClerkLogDatabase) =
        database.clerkLogDao()

}