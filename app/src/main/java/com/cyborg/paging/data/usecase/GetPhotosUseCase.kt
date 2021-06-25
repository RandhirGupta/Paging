package com.cyborg.paging.data.usecase

import com.cyborg.paging.data.executor.BaseSchedulerProvider
import com.cyborg.paging.data.model.PhotoModel
import com.cyborg.paging.data.repository.GetPhotosRepository
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetPhotosUseCase @Inject constructor(
    private val getPhotosRepository: GetPhotosRepository,
    private val schedulerProvider: BaseSchedulerProvider
) {
    fun getPhotos(
        text: String,
        perPage: Int
    ): Single<PhotoModel> = getPhotosRepository.getPhotosFromNetwork(
        "flickr.photos.search",
        "585ccfd3cc327b7052ea90264bf7ccb7",
        text,
        "json",
        1,
        perPage
    ).subscribeOn(schedulerProvider.io())
        .observeOn(schedulerProvider.ui())
}