package com.jishan.domain.mappers

import com.jishan.data.models.Wallpaper
import com.jishan.domain.entitiy.WallpaperEntity

object WallpaperMapper {
    fun toEntity(wallpaper: Wallpaper): WallpaperEntity {
        return WallpaperEntity(
            id = wallpaper.id,
            url = wallpaper.url,
            photographer = wallpaper.photographer
        )
    }
}
