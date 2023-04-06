import com.jishan.data.datasource.QuotesRemoteDataSource
import com.jishan.data.datasource.WallpapersRemoteDataSource
import com.jishan.data.network.QuotesApi
import com.jishan.data.network.QuotesService
import com.jishan.data.network.WallpapersApi
import com.jishan.data.network.WallpapersService
import com.jishan.data.repository.QuotesRepository
import com.jishan.data.repository.QuotesRepositoryImpl
import com.jishan.data.repository.WallpapersRepository
import com.jishan.data.repository.WallpapersRepositoryImpl
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
    fun provideQuotesRepository(quotesRemoteDataSource: QuotesRemoteDataSource): QuotesRepository {
        return QuotesRepositoryImpl(quotesRemoteDataSource)
    }

    @Provides
    fun provideWallpapersRepository(wallpapersRemoteDataSource: WallpapersRemoteDataSource): WallpapersRepository {
        return WallpapersRepositoryImpl(wallpapersRemoteDataSource)
    }

    @Provides
    fun provideQuotesRemoteDataSource(quotesService: QuotesService): QuotesRemoteDataSource {
        return QuotesRemoteDataSource(quotesService)
    }

    @Provides
    fun provideWallpapersRemoteDataSource(wallpapersService: WallpapersService): WallpapersRemoteDataSource {
        return WallpapersRemoteDataSource(wallpapersService)
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
    fun provideRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
