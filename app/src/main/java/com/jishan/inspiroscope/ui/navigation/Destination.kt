package com.jishan.inspiroscope.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.jishan.inspiroscope.R


sealed class Destination(val route: String, @StringRes val resourceId: Int, val icon: ImageVector) {
    object Home : Destination("home", R.string.home_route, Icons.Outlined.Home)
    object Theme : Destination("theme", R.string.theme_route, Icons.Outlined.Create)
    object Settings : Destination("settings", R.string.settings_route, Icons.Outlined.Settings)
}
