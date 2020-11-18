package com.example.myimage.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UnsplashPhoto (

    @field:SerializedName("description")
    val description: String? = null,
    @field:SerializedName("id")
    val id: String? = null,
    val urls :UnsplashPhotoUrls,
    val user : UnsplashUser

) : Parcelable
{

    @Parcelize
    data class UnsplashPhotoUrls(

        @field:SerializedName("small")
        val small: String? = null,

        @field:SerializedName("thumb")
        val thumb: String? = null,

        @field:SerializedName("raw")
        val raw: String? = null,

        @field:SerializedName("regular")
        val regular: String? = null,

        @field:SerializedName("full")
        val full: String? = null
    ): Parcelable

    @Parcelize
      data class UnsplashUser(

          @field:SerializedName("name")
          val name: String? = null,
          @field:SerializedName("username")
          val username: String? = null
      ): Parcelable
      {

          val attributionUrl get() = "https://unsplash.com/$username?utm_source=ImageSearchApp&utm_medium=referral"

      }

  }







