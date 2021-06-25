package com.cyborg.paging.data.usecase

import androidx.paging.PagedList
import com.cyborg.paging.data.entity.PhotoEntity
import com.cyborg.paging.data.executor.BaseSchedulerProvider
import com.cyborg.paging.data.repository.GetPhotosRepository
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.disposables.Disposable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetPhotosUseCase @Inject constructor(
    private val getPhotosRepository: GetPhotosRepository,
    private val schedulerProvider: BaseSchedulerProvider,
) {
    fun getPhotos(text: String): Flowable<PagedList<PhotoEntity>> =
        getPhotosRepository.fetchPhotosNextPage(
            text).toFlowable(BackpressureStrategy.BUFFER).subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())

    fun disposable(): Disposable? = getPhotosRepository.disposable()
}