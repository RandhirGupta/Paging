package com.cyborg.paging.data.repository

import com.cyborg.paging.data.model.PhotoModel
import io.reactivex.Single

interface GetPhotosRepository {
    fun getPhotosFromNetwork(
        method: String,
        apiKey: String,
        text: String,
        format: String,
        noJsonCallback: Int,
        perPage: Int
    ): Single<PhotoModel>
}