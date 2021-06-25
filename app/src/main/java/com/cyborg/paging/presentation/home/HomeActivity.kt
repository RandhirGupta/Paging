package com.cyborg.paging.presentation.home

import android.os.Bundle
import android.text.Editable
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import com.cyborg.paging.R
import com.cyborg.paging.data.entity.PhotoEntity
import com.cyborg.paging.databinding.ActivityHomeBinding
import com.cyborg.paging.presentation.base.BaseActivity
import com.cyborg.paging.presentation.common.DebounceEditText
import com.cyborg.paging.presentation.common.State
import com.cyborg.paging.presentation.home.adapter.PhotosAdapter
import com.cyborg.paging.presentation.home.viewmodel.HomeViewModel
import javax.inject.Inject

class HomeActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val homeViewModel: HomeViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
    }

    private lateinit var homeActivityBinding: ActivityHomeBinding
    private val photosAdapter: PhotosAdapter by lazy {
        PhotosAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        homeActivityBinding.lifecycleOwner = this

        homeActivityBinding.rvPhotos.adapter = photosAdapter
        observeSearchText()
        observePhotoFetch()
    }

    private fun observeSearchText() {
        homeActivityBinding.etSearch.registerDebounceTextChangeListener(object :
            DebounceEditText.TextChangeListener {
            override fun onTextChange(editable: Editable?) {
                editable?.let {
                    homeViewModel.getPhotos(it.toString())
                }
            }
        })
    }

    private fun observePhotoFetch() {
        homeViewModel.photosLiveEvent.observe(this, {
            when (it) {
                is State.Error -> showErrorView()
                is State.Loading -> showLoadingView()
                is State.Success -> showSuccessView(it.data)
            }
        })
    }

    private fun showLoadingView() {

    }

    private fun showErrorView() {

    }

    private fun showSuccessView(photoEntity: PagedList<PhotoEntity>) {
        photosAdapter.submitList(photoEntity)
    }
}