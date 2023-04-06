package com.jishan.data.datasource

import com.jishan.data.models.Wallpaper

interface WallpaperDataSource {
    suspend fun getRandomWallpaper(): Result<Wallpaper>
}
