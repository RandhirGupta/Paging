package com.cyborg.paging.data.local

import androidx.paging.DataSource
import com.cyborg.paging.data.entity.PhotoEntity
import javax.inject.Singleton

@Singleton
class LocalSourceImpl(private val photosDatabase: PhotosDatabase) : LocalSource {

    override fun insertPhotos(photos: List<PhotoEntity>) = photosDatabase.photoDao().insert(photos)

    override fun getPhotos(text: String): DataSource.Factory<Int, PhotoEntity> =
        photosDatabase.photoDao().getPhotos(text)
}