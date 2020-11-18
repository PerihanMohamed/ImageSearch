package com.example.myimage.di

import com.example.myimage.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule  {

//    @Provides
//    @Singleton
//         fun getApiService() : ApiService {
//         return Retrofit.Builder()
//             .baseUrl(ApiService.BASE_URL)
//             .addConverterFactory(GsonConverterFactory.create())
//             .build()
//             .create(ApiService::class.java)



    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(ApiService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideUnsplashApi(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    }

