package com.jishan.inspiroscope.ui.screen.theme.entities

import androidx.compose.ui.text.font.FontFamily

//todo : Parecble not works with FontFamily class , need to implement different approch to make it work with rememberSavable
data class Font(val title: String, val fontFamily: FontFamily)