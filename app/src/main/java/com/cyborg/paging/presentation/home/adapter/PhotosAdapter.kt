package com.cyborg.paging.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cyborg.paging.data.entity.PhotoEntity
import com.cyborg.paging.databinding.LayoutPhotoItemBinding
import com.cyborg.paging.presentation.common.getPhotoUrl
import kotlinx.android.synthetic.main.layout_photo_item.view.*

class PhotosAdapter :
    PagedListAdapter<PhotoEntity, PhotosAdapter.PhotoViewHolder>(photoDiffCallback) {

    companion object {
        private val photoDiffCallback = object : DiffUtil.ItemCallback<PhotoEntity>() {
            override fun areItemsTheSame(oldItem: PhotoEntity, newItem: PhotoEntity): Boolean {
                return oldItem.id == newItem.id &&
                        oldItem.farm == newItem.farm &&
                        oldItem.isFamily == newItem.isFamily &&
                        oldItem.isFriend == newItem.isFriend &&
                        oldItem.isPublic == newItem.isPublic &&
                        oldItem.owner == newItem.owner &&
                        oldItem.secret == newItem.secret &&
                        oldItem.server == newItem.server &&
                        oldItem.title == newItem.title &&
                        oldItem.text == newItem.text
            }

            override fun areContentsTheSame(oldItem: PhotoEntity, newItem: PhotoEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val dataBinding = LayoutPhotoItemBinding.inflate(layoutInflater, parent, false)
        return PhotoViewHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photoEntity = getItem(position)
        photoEntity?.let {
            holder.bindData(it)
        } ?: holder.clear()
    }

    inner class PhotoViewHolder(private var binding: LayoutPhotoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(photoEntity: PhotoEntity) {
            binding.tvTitle.text = photoEntity.title
            Glide.with(itemView.context).load(photoEntity.getPhotoUrl()).into(binding.ivPhoto)
        }

        fun clear() = itemView.run {
            tvTitle.invalidate()
            ivPhoto.invalidate()
        }
    }
}