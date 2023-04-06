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

    private val _quote = MutableStateFlow<QuoteEntity?>(null)
    val quote: StateFlow<QuoteEntity?> = _quote

    private val _wallpaper = MutableStateFlow<WallpaperEntity?>(null)
    val wallpaper: StateFlow<WallpaperEntity?> = _wallpaper

    init {
        loadRandomQuote()
        loadRandomWallpaper()
    }

    fun loadRandomQuote() {
        viewModelScope.launch {
            val result = getRandomQuoteUseCase()

            result.onSuccess { quoteEntity ->
                _quote.value = quoteEntity
            }

            result.onFailure { throwable ->
                // Handle failure case, show error message or update the UI
            }
        }
    }

    fun loadRandomWallpaper() {
        viewModelScope.launch {
            val result = getRandomWallpaperUseCase()

            result.onSuccess { wallpaperEntity ->
                _wallpaper.value = wallpaperEntity
            }

            result.onFailure { throwable ->
                // Handle failure case, show error message or update the UI
            }
        }
    }
}
