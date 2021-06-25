package com.cyborg.paging.data.pagedcallback

import com.cyborg.paging.data.entity.PhotoEntity
import com.cyborg.paging.data.model.Photo

interface PagedListCallback {
    fun onZeroItemsLoaded()

    fun onItemAtEndLoaded(itemAtEnd: PhotoEntity)
}