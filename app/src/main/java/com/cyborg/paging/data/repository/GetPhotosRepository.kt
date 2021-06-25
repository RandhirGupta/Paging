package com.cyborg.paging.data.repository

import androidx.paging.PagedList
import com.cyborg.paging.data.entity.PhotoEntity
import io.reactivex.Observable
import io.reactivex.disposables.Disposable

interface GetPhotosRepository {
    fun fetchPhotosNextPage(text: String): Observable<PagedList<PhotoEntity>>

    fun disposable(): Disposable?
}