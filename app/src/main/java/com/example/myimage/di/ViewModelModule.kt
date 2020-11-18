package com.example.myimage.di

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.myimage.data.UnsplashPhoto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ViewModelModule @ViewModelInject constructor (val repositoryModule: RepositoryModule): ViewModel() {

//    private var currentQueryValue: String? = null
//
//    private var currentSearchResult: Flow<PagingData<UnsplashPhoto>>? = null
//
//
//    fun searchRepo(queryString: String): Flow<PagingData<UnsplashPhoto>> {
//        val lastResult = currentSearchResult
//        if (queryString == currentQueryValue && lastResult != null) {
//            return lastResult
//        }
//        currentQueryValue = queryString
//        val newResult: Flow<PagingData<UnsplashPhoto>> = repositoryModule.getSearchPhotos(queryString)
//            .cachedIn(viewModelScope)
//        currentSearchResult = newResult
//        return newResult
//    }

    private val currentQuery = MutableLiveData(DEFAULT_QUERY)

    val photos = currentQuery.switchMap {queryString->
        repositoryModule.getSearchPhotos(queryString).cachedIn(viewModelScope)
    }

    fun searchPhotos(Query : String){

        currentQuery.value= Query

    }




    companion object{
         private const val DEFAULT_QUERY = "CAT"
    }




}