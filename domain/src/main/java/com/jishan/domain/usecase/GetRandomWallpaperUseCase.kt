package com.jishan.domain.usecase

import com.jishan.data.repository.WallpapersRepository
import com.jishan.domain.entitiy.WallpaperEntity
import com.jishan.domain.mappers.WallpaperMapper
import com.jishan.domain.mappers.WallpaperMapper.toEntity

class GetRandomWallpaperUseCase(private val wallpapersRepository: WallpapersRepository) {
    suspend fun execute(): Result<WallpaperEntity> {
        val result = wallpapersRepository.getRandomWallpaper()
        return result.map(WallpaperMapper::toEntity)
    }
}
