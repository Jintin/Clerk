package com.jintin.clerk.app.obj

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**
 * ClerkLog object
 */
@Parcelize
@Entity(tableName = "logs")
data class ClerkLog(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val app: String,
    val channel: String,
    val log: String
) : Parcelable
