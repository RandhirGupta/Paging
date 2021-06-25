package com.cyborg.paging.data.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cyborg.paging.data.local.PhotosDatabase
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = PhotosDatabase.TABLE_PHOTOS)
data class PhotoEntity(
    @PrimaryKey
    val id: String,

    val farm: Int,

    val isFamily: Int,

    val isFriend: Int,

    val isPublic: Int,

    val owner: String,

    val secret: String,

    val server: String,

    val title: String,

    val text: String,
) : Parcelable