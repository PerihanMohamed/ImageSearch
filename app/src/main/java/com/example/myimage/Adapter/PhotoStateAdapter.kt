package com.example.myimage.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myimage.databinding.ItemLoadStateBinding

class PhotoStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<PhotoStateAdapter.LoadStateViewHolder>()

{


    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {

        holder.bindPost(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding = ItemLoadStateBinding
            .inflate(LayoutInflater.from(parent.context ) , parent ,false)
        return LoadStateViewHolder(binding)
    }



    inner class LoadStateViewHolder(val binding:ItemLoadStateBinding): RecyclerView.ViewHolder(binding.root){

        init {
            binding.buttonRetry.setOnClickListener {
                retry.invoke()
            }
        }


              fun bindPost(loadState: LoadState){

                  binding.apply {



                      progressBar.isVisible = loadState is LoadState.Loading
                      buttonRetry.isVisible = loadState !is LoadState.Loading
                      textViewError.isVisible = loadState !is LoadState.Loading

                  }

              }
    }

}