package com.jishan.data.network

import com.jishan.data.models.WallpaperResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WallpapersService {
    private val baseUrl = "https://api.unsplash.com/"

    private val wallpapersApi: WallpapersApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        wallpapersApi = retrofit.create(WallpapersApi::class.java)
    }

    suspend fun getRandomWallpaper(): Response<WallpaperResponse> {
        return wallpapersApi.getRandomWallpaper()
    }
}
