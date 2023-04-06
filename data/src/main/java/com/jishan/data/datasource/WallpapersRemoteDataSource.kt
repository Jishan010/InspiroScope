package com.jishan.data.datasource

import com.google.gson.Gson
import com.jishan.data.models.ErrorResponse
import com.jishan.data.models.Wallpaper
import com.jishan.data.models.WallpaperResponse
import com.jishan.data.network.WallpapersService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class WallpapersRemoteDataSource(private val wallpapersService: WallpapersService) :
    WallpaperDataSource {

    override suspend fun getRandomWallpaper(): Result<Wallpaper> = withContext(Dispatchers.IO) {
        try {
            val response = wallpapersService.getRandomWallpaper()
            if (response.isSuccessful) {
                val wallpaperResponse = response.body()
                if (wallpaperResponse != null) {
                    Result.success(Wallpaper(wallpaperResponse))
                } else {
                    Result.failure(Exception("No wallpaper found"))
                }
            } else {
                val errorResponse = parseErrorResponse(response)
                Result.failure(Exception(errorResponse?.error?.message ?: "Unknown error"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun parseErrorResponse(response: Response<WallpaperResponse>): ErrorResponse? {
        val gson = Gson()
        return try {
            gson.fromJson(response.errorBody()?.charStream(), ErrorResponse::class.java)
        } catch (e: Exception) {
            null
        }
    }
}
