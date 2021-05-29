package com.example.app.presentation.api

import com.example.app.presentation.list.Photo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface PhotoApi {
    @GET("/v2/list")
    fun getPhotoList(): Call<List<Photo>>

    @GET("/id/{id}/info")
    fun getPhotoDetail(@Path("id") id: Int): Call<PhotoDetailResponse>
}