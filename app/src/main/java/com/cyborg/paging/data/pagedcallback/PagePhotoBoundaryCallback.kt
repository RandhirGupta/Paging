package com.cyborg.paging.data.pagedcallback

import androidx.paging.PagedList
import com.cyborg.paging.data.entity.PhotoEntity
import io.reactivex.disposables.Disposable

class PagePhotoBoundaryCallback(
    private val pagedListCallback: PagedListCallback?,
) :
    PagedList.BoundaryCallback<PhotoEntity>() {

    var isRunning = false
    var currentPage = 1
    var disposable: Disposable? = null

    override fun onZeroItemsLoaded() {
        pagedListCallback?.onZeroItemsLoaded()
    }

    override fun onItemAtEndLoaded(itemAtEnd: PhotoEntity) {
        pagedListCallback?.onItemAtEndLoaded(itemAtEnd)
    }
}