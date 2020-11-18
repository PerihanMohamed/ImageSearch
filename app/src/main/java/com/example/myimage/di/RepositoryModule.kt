package com.example.myimage.di

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.myimage.api.ApiService
import com.example.myimage.data.UnsplashPagingSource
import com.example.myimage.data.UnsplashPhoto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryModule @Inject constructor ( private val apiService: ApiService) {



    fun getSearchPhotos(query :String) =

        Pager(
           config = PagingConfig(
               pageSize = 20,
               maxSize = 100,
               enablePlaceholders = false

           ),  pagingSourceFactory = { UnsplashPagingSource(apiService, query) }

        ).liveData



}