package com.jishan.data.repository

import com.jishan.data.datasource.WallpaperDataSource
import com.jishan.data.models.Wallpaper

class WallpapersRepositoryImpl(private val wallpapersDataSource: WallpaperDataSource) : WallpapersRepository {
    override suspend fun getRandomWallpaper(): Result<Wallpaper> {
        return wallpapersDataSource.getRandomWallpaper()
    }
}
