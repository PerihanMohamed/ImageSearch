package com.example.myimage.Adapter

import android.renderscript.ScriptGroup
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.myimage.R
import com.example.myimage.data.UnsplashPhoto
import com.example.myimage.databinding.FragmentGalleryBinding
import com.example.myimage.databinding.ItemUnsplashBinding
import kotlinx.android.synthetic.main.item_unsplash.view.*

class PagingAdapter (private val listener: OnItemClickListener) : PagingDataAdapter<UnsplashPhoto,PagingAdapter.PhotoViewHolder >(PhotoComparator) {


    inner class PhotoViewHolder(private val binding: ItemUnsplashBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindPost(photo: UnsplashPhoto) {
            binding.apply {
                Glide
                    .with(itemView)
                    .load(photo.urls.regular)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_user)
                    .into(imageView)
                textViewUserName.text = photo.user.username
            }
        }

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    if (item != null) {
                        listener.onItemClick(item)
                    }
                }
            }
        }
    }








    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null) {
            holder.bindPost(currentItem)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding = ItemUnsplashBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoViewHolder(binding)
    }

      companion object PhotoComparator : DiffUtil.ItemCallback<UnsplashPhoto>() {
        override fun areItemsTheSame(oldItem: UnsplashPhoto, newItem: UnsplashPhoto): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: UnsplashPhoto,
            newItem: UnsplashPhoto
        ): Boolean {
            return oldItem == newItem
        }
    }

    interface OnItemClickListener  {
        fun onItemClick(photo: UnsplashPhoto)
    }

}












