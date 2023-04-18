package com.jishan.inspiroscope.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jishan.domain.entitiy.QuoteEntity
import com.jishan.domain.entitiy.WallpaperEntity
import com.jishan.domain.usecase.GetRandomQuoteUseCase
import com.jishan.domain.usecase.GetRandomWallpaperUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// todo add batching and caching mechanism implementation to make vertical scrooling smoother without letting user wait for data
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getRandomQuoteUseCase: GetRandomQuoteUseCase,
    private val getRandomWallpaperUseCase: GetRandomWallpaperUseCase
) : ViewModel() {

    private val _data = MutableStateFlow<List<DataItem>>(emptyList())
    val data: StateFlow<List<DataItem>> = _data

    private var isLoading = false
    private var nextPageToLoad = 0

    init {
        loadNextData(0)
    }

    fun loadNextData(currentPage: Int) {
        if (isLoading) return
        if (currentPage >= nextPageToLoad - 1) {
            viewModelScope.launch {
                isLoading = true

                // with async - await both of the coroutine or suspend function for fetching news and wallaper will run concurrently and will give the result at once.
                val quoteDeferred = async { getRandomQuoteUseCase.invoke() }
                val wallpaperDeferred = async { getRandomWallpaperUseCase.invoke() }

                val quoteResult = quoteDeferred.await()
                val wallpaperResult = wallpaperDeferred.await()

                if (quoteResult.isSuccess && wallpaperResult.isSuccess) {
                    val newDataItem =
                        DataItem(quoteResult.getOrNull()!!, wallpaperResult.getOrNull()!!)
                    _data.value = _data.value + newDataItem

                    nextPageToLoad++
                } else {
                    // Handle errors, you can use quoteResult.exceptionOrNull() and wallpaperResult.exceptionOrNull()
                }
                isLoading = false
            }
        }
    }

    data class DataItem(val quote: QuoteEntity, val wallpaper: WallpaperEntity)
}
