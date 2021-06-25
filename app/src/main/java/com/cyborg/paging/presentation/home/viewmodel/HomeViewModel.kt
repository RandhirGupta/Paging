package com.cyborg.paging.presentation.home.viewmodel

import android.util.Log
import androidx.paging.PagedList
import com.cyborg.paging.data.entity.PhotoEntity
import com.cyborg.paging.data.usecase.GetPhotosUseCase
import com.cyborg.paging.presentation.base.BaseViewModel
import com.cyborg.paging.presentation.common.SingleLiveEvent
import com.cyborg.paging.presentation.common.State
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val getPhotosUseCase: GetPhotosUseCase) :
    BaseViewModel() {

    companion object {
        private const val TAG = "HomeViewModel"
    }

    init {
        getPhotosUseCase.disposable()?.run { compositeDisposable.add(this) }
    }

    val photosLiveEvent: SingleLiveEvent<State<PagedList<PhotoEntity>>> = SingleLiveEvent()

    fun getPhotos(text: String) {
        getPhotosUseCase.getPhotos(text).doOnError {
            photosLiveEvent.postValue(State.error("Error", it))
        }.subscribeBy(
            onNext = {
                photosLiveEvent.postValue(State.success(it))
            }, onError = {
                photosLiveEvent.postValue(State.error("Error", it))
            }, onComplete = {
                Log.i(TAG, "Completed")
            }
        ).addTo(compositeDisposable)
    }
}