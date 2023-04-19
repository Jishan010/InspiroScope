package com.jishan.inspiroscope.ui.screen.home

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jishan.domain.entitiy.QuoteEntity
import com.jishan.domain.entitiy.WallpaperEntity
import com.jishan.domain.usecase.GetRandomQuoteUseCase
import com.jishan.domain.usecase.GetRandomWallpaperUseCase

class RandomQuoteWallpaperPagingSource(
    private val getRandomQuoteUseCase: GetRandomQuoteUseCase,
    private val getRandomWallpaperUseCase: GetRandomWallpaperUseCase
) : PagingSource<Int, Pair<QuoteEntity, WallpaperEntity>>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pair<QuoteEntity, WallpaperEntity>> {
        return try {
            val quoteResult = getRandomQuoteUseCase.invoke()
            val wallpaperResult = getRandomWallpaperUseCase.invoke()

            quoteResult.fold(
                onFailure = { throw it },
                onSuccess = { quote ->
                    wallpaperResult.fold(
                        onFailure = { throw it },
                        onSuccess = { wallpaper ->
                            val data = listOf(Pair(quote, wallpaper))
                            LoadResult.Page(
                                data = data,
                                prevKey = params.key?.minus(1),
                                nextKey = params.key?.plus(1)
                            )
                        }
                    )
                }
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Pair<QuoteEntity, WallpaperEntity>>): Int? {
        return state.anchorPosition
    }
}
