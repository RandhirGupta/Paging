package com.cyborg.paging.data.network

import com.cyborg.paging.data.Query.API_KEY
import com.cyborg.paging.data.Query.FORMAT
import com.cyborg.paging.data.Query.METHOD
import com.cyborg.paging.data.Query.NO_JSON_CALLBACK
import com.cyborg.paging.data.Query.PAGE
import com.cyborg.paging.data.Query.PER_PAGE
import com.cyborg.paging.data.Query.TEXT
import com.cyborg.paging.data.model.PhotoModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("services/rest/")
    fun getPhotos(
        @Query(METHOD)
        method: String,
        @Query(API_KEY)
        apiKey: String,
        @Query(TEXT)
        text: String,
        @Query(FORMAT)
        format: String,
        @Query(NO_JSON_CALLBACK)
        noJsonCallback: Int,
        @Query(PER_PAGE)
        perPage: Int,
        @Query(PAGE)
        page: Int,
    ): Single<PhotoModel>
}