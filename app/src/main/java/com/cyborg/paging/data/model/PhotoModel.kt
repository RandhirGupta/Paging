package com.cyborg.paging.data.model


import com.google.gson.annotations.SerializedName

data class PhotoModel(
    @SerializedName("photos")
    val photos: Photos,

    @SerializedName("stat")
    val stat: String
)

data class Photos(
    @SerializedName("page")
    val page: Int,

    @SerializedName("pages")
    val pages: Int,

    @SerializedName("perpage")
    val perPage: Int,

    @SerializedName("photo")
    val photo: List<Photo>,

    @SerializedName("total")
    val total: Int
)

data class Photo(
    @SerializedName("farm")
    val farm: Int,

    @SerializedName("id")
    val id: String,

    @SerializedName("isfamily")
    val isFamily: Int,

    @SerializedName("isfriend")
    val isFriend: Int,
    @SerializedName("ispublic")
    val isPublic: Int,

    @SerializedName("owner")
    val owner: String,

    @SerializedName("secret")
    val secret: String,

    @SerializedName("server")
    val server: String,

    @SerializedName("title")
    val title: String
)