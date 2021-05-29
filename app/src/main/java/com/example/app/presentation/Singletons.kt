package com.example.app.presentation

import com.example.app.presentation.PhotoApplication.Companion.context
import com.example.app.presentation.api.PhotoApi
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

class Singletons(){
    companion object{
        var cache = Cache( File(context?.cacheDir, "responses"),  10 * 1024 * 1024)// 10 MiB
        val okHttpClient: OkHttpClient = OkHttpClient().newBuilder()
            .cache(cache)
            .build()


        val photoApi: PhotoApi = Retrofit.Builder()
            .baseUrl("https://picsum.photos")
            .addConverterFactory(GsonConverterFactory.create())
             .client(okHttpClient)
            .build().create(PhotoApi::class.java)
    }
}
