package com.jishan.data.network

import com.jishan.data.models.WallpaperResponse
import retrofit2.Response

class WallpapersService(private val wallpapersApi: WallpapersApi) {

    suspend fun getRandomWallpaper(): Response<WallpaperResponse> {
        return wallpapersApi.getRandomWallpaper()
    }
}
