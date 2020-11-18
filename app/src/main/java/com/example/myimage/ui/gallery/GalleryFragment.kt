package com.example.myimage.ui.gallery

import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

import androidx.lifecycle.observe
import com.example.myimage.Adapter.PagingAdapter
import com.example.myimage.Adapter.PhotoStateAdapter
import com.example.myimage.R
import com.example.myimage.databinding.FragmentGalleryBinding
import com.example.myimage.di.ViewModelModule
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_gallery.*
import android.app.SearchManager
import android.widget.AdapterView

import android.widget.SearchView.OnQueryTextListener
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuItemCompat
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController


import androidx.paging.LoadState
import com.example.myimage.data.UnsplashPhoto

@AndroidEntryPoint
class GalleryFragment: Fragment(R.layout.fragment_gallery), PagingAdapter.OnItemClickListener {

    var _binding :FragmentGalleryBinding ? = null
    private val binding get() =  _binding!!

    private val viewModel by viewModels<ViewModelModule>()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = PagingAdapter(this)

        _binding = FragmentGalleryBinding.bind(view)

        binding.apply {
            recycler_view.adapter = adapter.withLoadStateHeaderAndFooter(
                footer = PhotoStateAdapter {adapter.retry()} ,
                header = PhotoStateAdapter {adapter.retry()},

                )
            recycler_view.setHasFixedSize(true)

        }

        viewModel.photos.observe(viewLifecycleOwner ){
                  adapter.submitData( viewLifecycleOwner.lifecycle , it)

        }

      adapter.addLoadStateListener { loadState ->
          binding.apply {
              progress_bar.isVisible= loadState.source.refresh is LoadState.Loading
              text_view_error.isVisible = loadState.source.refresh is LoadState.NotLoading
              button_retry.isVisible = loadState.source.refresh is LoadState.Error
              text_view_empty.isVisible = loadState.source.refresh is LoadState.Error

              if ( loadState.source.refresh is LoadState.NotLoading
                  &&  loadState.append.endOfPaginationReached &&
                  adapter.itemCount < 1
              ){
                  recyclerView.isVisible = false
                  textViewEmpty.isVisible = true
              }else {

                  textViewEmpty.isVisible = false
              }


          }

      }








        setHasOptionsMenu(true)


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menugallery, menu)

        val searchItem = menu.findItem(R.id.app_bar_search)

        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object :  SearchView.OnQueryTextListener {

            override fun onQueryTextChange(query: String): Boolean {

                return true

            }

            override fun onQueryTextSubmit(query: String): Boolean {
                if (query != null) {
                    binding.recyclerView.scrollToPosition(0)
                    viewModel.searchPhotos(query)
                    searchView.clearFocus()
                }
                return true
            }

        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(photo: UnsplashPhoto) {
       val action = GalleryFragmentDirections.actionGalleryFragmentToDetailFragment(photo)
        findNavController().navigate(action)
    }


}