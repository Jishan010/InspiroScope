package com.jishan.data.repository

import com.jishan.data.models.Wallpaper

interface WallpapersRepository {
    suspend fun getRandomWallpaper(): Result<Wallpaper>
}
