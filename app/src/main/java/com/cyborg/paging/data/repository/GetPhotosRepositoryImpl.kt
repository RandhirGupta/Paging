package com.cyborg.paging.data.repository

import android.util.Log
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.cyborg.paging.data.Query
import com.cyborg.paging.data.entity.PhotoEntity
import com.cyborg.paging.data.executor.BaseSchedulerProvider
import com.cyborg.paging.data.local.LocalSource
import com.cyborg.paging.data.network.ApiService
import com.cyborg.paging.data.pagedcallback.PagePhotoBoundaryCallback
import com.cyborg.paging.data.pagedcallback.PagedListCallback
import com.cyborg.paging.presentation.common.toPhotoEntity
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetPhotosRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val localSource: LocalSource,
    private val schedulerProvider: BaseSchedulerProvider,
) : GetPhotosRepository, PagedListCallback {

    companion object {
        private const val TAG = "GetPhotosRepositoryImpl"
    }

    private val pagePhotoBoundaryCallback = PagePhotoBoundaryCallback(this)
    private var text = ""
    private var perPage = Query.PER_PAGE_VALUE
    private var disposable: Disposable? = null

    override fun fetchPhotosNextPage(text: String): Observable<PagedList<PhotoEntity>> {

        if (this.text != text) {
            pagePhotoBoundaryCallback.currentPage = 0
        }

        this.text = text
        return RxPagedListBuilder(localSource.getPhotos(text),
            PagedList.Config.Builder().setPrefetchDistance(5).setPageSize(Query.PER_PAGE_VALUE)
                .build())
            .setBoundaryCallback(PagePhotoBoundaryCallback(this))
            .buildObservable()
    }

    override fun disposable(): Disposable? = disposable

    override fun onZeroItemsLoaded() {
        fetchAndStorePhotos()
    }

    override fun onItemAtEndLoaded(itemAtEnd: PhotoEntity) {
        fetchAndStorePhotos()
    }

    private fun fetchAndStorePhotos() {
        if (pagePhotoBoundaryCallback.isRunning) return
        pagePhotoBoundaryCallback.isRunning = true

        disposable = apiService.getPhotos(
            Query.METHOD_PHOTO_SEARCH_VALUE,
            Query.API_KEY_VALUE,
            text,
            Query.FORMAT_JSON,
            Query.NO_JSON_CALLBACK_VALUE,
            perPage,
            pagePhotoBoundaryCallback.currentPage
        ).map {
            it.photos.photo.map { photo ->
                photo.toPhotoEntity(text)
            }
        }.doOnSuccess {
            if (it.isNotEmpty()) {
                localSource.insertPhotos(it)
                Log.i(TAG, "Inserted: ${it.size}")
            } else {
                Log.i(TAG, "No Inserted")
            }
            pagePhotoBoundaryCallback.currentPage++
        }.ignoreElement()
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .doFinally {
                pagePhotoBoundaryCallback.isRunning = false
            }.subscribe({ Log.i(TAG, "Photo Fetch Completed") }, { it.printStackTrace() })
    }
}