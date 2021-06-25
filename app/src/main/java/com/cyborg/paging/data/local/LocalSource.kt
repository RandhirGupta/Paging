package com.cyborg.paging.data.local

import androidx.paging.DataSource
import com.cyborg.paging.data.entity.PhotoEntity

interface LocalSource {
    fun insertPhotos(photos: List<PhotoEntity>)

    fun getPhotos(text: String): DataSource.Factory<Int, PhotoEntity>
}