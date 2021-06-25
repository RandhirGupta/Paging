package com.cyborg.paging.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cyborg.paging.data.entity.PhotoEntity
import com.cyborg.paging.data.local.PhotosDatabase.Companion.DATABASE_NAME
import com.cyborg.paging.presentation.common.SingletonHolder

@Database(entities = [PhotoEntity::class], version = 1)
abstract class PhotosDatabase : RoomDatabase() {

    companion object : SingletonHolder<PhotosDatabase, Context>({
        Room.databaseBuilder(
            it.applicationContext,
            PhotosDatabase::class.java,
            DATABASE_NAME).build()
    }) {
        internal const val DATABASE_NAME = "photos.db"
        internal const val TABLE_PHOTOS = "photos"
    }

    abstract fun photoDao(): PhotosDao
}