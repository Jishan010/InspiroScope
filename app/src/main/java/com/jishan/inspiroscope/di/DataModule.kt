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
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideQuotesRepository(quotesDataSource: QuotesDataSource): QuotesRepository {
        return QuotesRepositoryImpl(quotesDataSource)
    }

    @Singleton
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
        return provideRetrofit("https://favqs.com/api/").create(QuotesApi::class.java)
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
        val clientId =
            "LZfrL_ANr1HXrv38aJd4KT8ls_6R_dB6OAK3GjIWYgM" // Replace this with a secure way of storing the API key
        return WallpapersService(wallpapersApi, clientId)
    }

    @Provides
    fun provideGetRandomQuoteUseCase(quotesRepository: QuotesRepository): GetRandomQuoteUseCase {
        return GetRandomQuoteUseCase(quotesRepository)
    }

    @Provides
    fun provideGetRandomWallpaperUseCase(wallpapersRepository: WallpapersRepository): GetRandomWallpaperUseCase {
        return GetRandomWallpaperUseCase(wallpapersRepository)
    }

    @Singleton
    @Provides
    fun provideRetrofit(baseUrl: String): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}
