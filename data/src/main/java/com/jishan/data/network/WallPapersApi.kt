package com.jishan.data.network

import com.jishan.data.models.WallpaperResponse
import retrofit2.http.GET

interface WallpapersApi {
    @GET("api/wallpapers/random")
    suspend fun getRandomWallpaper(): WallpaperResponse
}