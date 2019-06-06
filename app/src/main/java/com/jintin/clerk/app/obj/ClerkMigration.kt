package com.jintin.clerk.app.obj

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.jintin.clerk.lib.LogType


object ClerkMigration {

    fun getMigrations(): Array<Migration> {
        return arrayOf(getMigration1To2)
    }

    private val getMigration1To2: Migration = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE logs ADD COLUMN logType TINYINT NOT NULL DEFAULT ${LogType.NONE.ordinal}")
        }
    }
}