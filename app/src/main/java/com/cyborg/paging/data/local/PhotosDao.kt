package com.cyborg.paging.data.local

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cyborg.paging.data.entity.PhotoEntity

@Dao
interface PhotosDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(photos: List<PhotoEntity>)

    @Query("SELECT * FROM ${PhotosDatabase.TABLE_PHOTOS} WHERE text = :text")
    fun getPhotos(text: String): DataSource.Factory<Int, PhotoEntity>
}