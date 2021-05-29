package com.example.app.presentation.api

data class PhotoDetailResponse(
    val author: String,
    val id:Int,
    val url: String,
    val download_url:String
)