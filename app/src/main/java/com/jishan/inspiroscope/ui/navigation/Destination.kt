package com.jishan.inspiroscope.ui.navigation

sealed class Destination(val route: String) {
    object Home : Destination("home")
    object Theme : Destination("theme")
    object Settings : Destination("settings")
}
