package com.jishan.inspiroscope.ui.screen.theme.entities

import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.ui.text.font.FontFamily

data class Font(val title: String, val fontFamily: FontFamily)

// Parecble not works with FontFamily class , so implemented below approach to make it work with rememberSavable
// todo look into different solution as below solution is not working
val FontSaver = run {
    val fontTitleKey = "fontTitle"
    val fontFamilyKey = "fontFamily"
    mapSaver(
        save = { mapOf(fontTitleKey to it.title, fontFamilyKey to it.fontFamily) },
        restore = { Font(it[fontTitleKey] as String, it[fontFamilyKey] as FontFamily) },
    )
}
