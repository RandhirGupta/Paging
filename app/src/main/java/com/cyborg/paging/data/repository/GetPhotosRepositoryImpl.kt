package com.cyborg.paging.data.repository

import com.cyborg.paging.data.model.PhotoModel
import com.cyborg.paging.data.network.ApiService
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetPhotosRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    GetPhotosRepository {

    override fun getPhotosFromNetwork(
        method: String,
        apiKey: String,
        text: String,
        format: String,
        noJsonCallback: Int,
        perPage: Int
    ): Single<PhotoModel> =
        apiService.getPhotos(method, apiKey, text, format, noJsonCallback, perPage)
}