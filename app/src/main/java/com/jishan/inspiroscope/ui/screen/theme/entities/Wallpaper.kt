package com.jishan.inspiroscope.ui.screen.theme.entities

import androidx.compose.ui.graphics.Color
import com.jishan.inspiroscope.R

data class Wallpaper(
    val title: String,
    val color: Color,
    val imageResId: Int = R.drawable.first_wallpaper
)