package com.cyborg.paging.presentation.home.viewmodel

import com.cyborg.paging.data.usecase.GetPhotosUseCase
import com.cyborg.paging.presentation.base.BaseViewModel
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val getPhotosUseCase: GetPhotosUseCase) :
    BaseViewModel()