package com.jishan.inspiroscope.di

import com.jishan.data.datasource.QuotesDataSource
import com.jishan.data.datasource.QuotesRemoteDataSourceImpl
import com.jishan.data.datasource.WallpaperDataSource
import com.jishan.data.datasource.WallpapersRemoteDataSourceImpl
import com.jishan.data.network.QuotesApi
import com.jishan.data.network.QuotesService
import com.jishan.data.network.WallpapersApi
import com.jishan.data.network.WallpapersService
import com.jishan.data.repository.QuotesRepository
import com.jishan.data.repository.QuotesRepositoryImpl
import com.jishan.data.repository.WallpapersRepository
import com.jishan.data.repository.WallpapersRepositoryImpl
import com.jishan.domain.usecase.GetRandomQuoteUseCase
import com.jishan.domain.usecase.GetRandomWallpaperUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideQuotesRepository(quotesDataSource: QuotesDataSource): QuotesRepository {
        return QuotesRepositoryImpl(quotesDataSource)
    }

    @Provides
    fun provideWallpapersRepository(wallpaperDataSource: WallpaperDataSource): WallpapersRepository {
        return WallpapersRepositoryImpl(wallpaperDataSource)
    }

    @Provides
    fun provideQuotesRemoteDataSource(quotesService: QuotesService): QuotesDataSource {
        return QuotesRemoteDataSourceImpl(quotesService)
    }

    @Provides
    fun provideWallpapersRemoteDataSource(wallpapersService: WallpapersService): WallpaperDataSource {
        return WallpapersRemoteDataSourceImpl(wallpapersService)
    }

    @Provides
    fun provideQuotesApi(): QuotesApi {
        return provideRetrofit("https://quotes.rest/").create(QuotesApi::class.java)
    }

    @Provides
    fun provideWallpapersApi(): WallpapersApi {
        return provideRetrofit("https://api.unsplash.com/").create(WallpapersApi::class.java)
    }

    @Provides
    fun provideQuotesService(quotesApi: QuotesApi): QuotesService {
        return QuotesService(quotesApi)
    }

    @Provides
    fun provideWallpapersService(wallpapersApi: WallpapersApi): WallpapersService {
        return WallpapersService(wallpapersApi)
    }

    @Provides
    fun provideGetRandomQuoteUseCase(quotesRepository: QuotesRepository): GetRandomQuoteUseCase {
        return GetRandomQuoteUseCase(quotesRepository)
    }

    @Provides
    fun provideGetRandomWallpaperUseCase(wallpapersRepository: WallpapersRepository): GetRandomWallpaperUseCase {
        return GetRandomWallpaperUseCase(wallpapersRepository)
    }

    @Provides
    fun provideRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
