package com.jishan.inspiroscope.ui.screen.theme.entities

import android.os.Parcelable
import com.jishan.inspiroscope.R
import kotlinx.android.parcel.Parcelize

// Made this class Parcebale to make it work with rememberSavable
@Parcelize
data class Wallpaper(
    val title: String,
    val imageResId: Int = R.drawable.first_wallpaper,
) : Parcelable
