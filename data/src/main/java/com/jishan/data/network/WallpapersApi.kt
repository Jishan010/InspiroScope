package com.jishan.data.network

import com.jishan.data.models.WallpaperResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WallpapersApi {
    @GET("photos/random")
    suspend fun getRandomWallpaper(@Query("client_id") clientId: String): Response<WallpaperResponse>
}
