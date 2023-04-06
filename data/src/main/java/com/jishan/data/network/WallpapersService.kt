package com.jishan.data.network

import com.jishan.data.models.WallpaperResponse
import retrofit2.Response

class WallpapersService(private val wallpapersApi: WallpapersApi, private val clientId: String) {

    suspend fun getRandomWallpaper(): Response<WallpaperResponse> {
        return wallpapersApi.getRandomWallpaper(clientId)
    }
}
