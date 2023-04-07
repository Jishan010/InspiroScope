package com.jishan.inspiroscope.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jishan.domain.entitiy.QuoteEntity
import com.jishan.domain.entitiy.WallpaperEntity
import com.jishan.domain.usecase.GetRandomQuoteUseCase
import com.jishan.domain.usecase.GetRandomWallpaperUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

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

                val quoteResult = getRandomQuoteUseCase.invoke()
                val wallpaperResult = getRandomWallpaperUseCase.invoke()

                quoteResult.onSuccess { quoteEntity ->
                    wallpaperResult.onSuccess { wallpaperEntity ->
                        val newDataItem = DataItem(quoteEntity, wallpaperEntity)
                        _data.value = _data.value + newDataItem

                        nextPageToLoad++
                        isLoading = false
                    }
                }
            }
        }
    }

    data class DataItem(val quote: QuoteEntity, val wallpaper: WallpaperEntity)
}
