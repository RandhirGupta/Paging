package com.cyborg.paging.data.network

import com.cyborg.paging.data.model.PhotoModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("services/rest/")
    fun getPhotos(
        @Query("method")
        method: String,

        @Query("api_key")
        apiKey: String,

        @Query("text")
        text: String,

        @Query("format")
        format: String,

        @Query("nojsoncallback")
        noJsonCallback: Int,

        @Query("per_page")
        perPage: Int,
    ): Single<PhotoModel>
}