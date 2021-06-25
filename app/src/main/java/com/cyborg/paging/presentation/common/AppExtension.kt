package com.cyborg.paging.presentation.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import com.cyborg.paging.data.entity.PhotoEntity
import com.cyborg.paging.data.model.Photo
import io.reactivex.Flowable
import org.reactivestreams.Publisher

fun <T> Publisher<T>.toLiveData() = LiveDataReactiveStreams.fromPublisher(this) as LiveData<T>

fun <T> Flowable<T>.toState(): Flowable<State<T>> {
    return compose { item ->
        item
            .map { State.success(it) }
            .startWith(State.loading())
            .onErrorReturn { e ->
                State.error(
                    e.message ?: "Unknown Error",
                    e
                )
            }
    }
}

fun PhotoEntity.getPhotoUrl(): String {
    return "https://farm${farm}.staticflickr.com/$server/${id}_${secret}_m.jpg"
}

fun Photo.toPhotoEntity(text: String) =
    PhotoEntity(id, farm, isFamily, isFriend, isPublic, owner, secret, server, title, text)
