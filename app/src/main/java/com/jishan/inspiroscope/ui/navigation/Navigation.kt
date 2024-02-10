package com.jishan.inspiroscope.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jishan.inspiroscope.R
import com.jishan.inspiroscope.ui.screen.home.HomeScreen
import com.jishan.inspiroscope.ui.screen.theme.ThemeScreen
import com.jishan.inspiroscope.ui.screen.theme.entities.Font
import com.jishan.inspiroscope.ui.screen.theme.entities.Sound
import com.jishan.inspiroscope.ui.screen.theme.entities.Wallpaper
import com.jishan.inspiroscope.ui.theme.BreeSerifRegular
import com.jishan.inspiroscope.ui.theme.DeliciousHandrawn
import com.jishan.inspiroscope.ui.theme.DynaPuff
import com.jishan.inspiroscope.ui.theme.IndieFlower
import com.jishan.inspiroscope.ui.theme.MarkScriptRegular
import com.jishan.inspiroscope.ui.theme.VtThreeThreeThreeRegular

@Composable
fun MainNavigation(innerPadding: PaddingValues, navController: NavHostController) {
    var selectedWallpaper by remember { mutableStateOf<Wallpaper?>(null) }

    NavHost(
        navController = navController,
        startDestination = Destination.Home.route,
        Modifier.padding(innerPadding)
    ) {
        // local static data for  Theme Screen
        val wallpapers = listOf(
            Wallpaper("Wallpaper 1", R.drawable.first_wallpaper),
            Wallpaper("Wallpaper 2", R.drawable.second_wallpaper),
            Wallpaper("Wallpaper 4", R.drawable.fourth_wallpaper),
            Wallpaper("Wallpaper 6", R.drawable.sixth_wallpaper),
            Wallpaper("Wallpaper 7", R.drawable.seventh_wallpaper),
            Wallpaper("Wallpaper 8", R.drawable.eighth_wallaper),
            Wallpaper("Wallpaper 9", R.drawable.ninth_wallpaper),
        )

        val fonts = listOf(
            Font("Dyna Puff", DynaPuff),
            Font("Bree Serif", BreeSerifRegular),
            Font("Delicious Handrawn", DeliciousHandrawn),
            Font("Indie Flower", IndieFlower),
            Font("Mark Script", MarkScriptRegular),
            Font("Vt333", VtThreeThreeThreeRegular),
        )

        val sounds = listOf(
            Sound("Sound 1", R.drawable.first_sound),
            Sound("Sound 2", R.drawable.second_sound),
            Sound("Sound 3", R.drawable.third_sound),
            Sound("Sound 4", R.drawable.fourth_sound),
            Sound("Sound 5", R.drawable.fifth_sound),
            Sound("Sound 6", R.drawable.sixth_sound),
            Sound("Sound 7", R.drawable.seventh_sound),
        )

        composable(Destination.Home.route) { HomeScreen(selectedWallpaper) }
        composable(Destination.Theme.route) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background,
            ) {
                ThemeScreen(
                    wallpapers = wallpapers,
                    fonts = fonts,
                    sounds = sounds,
                    onWallpaperSelected = { wallpaper -> selectedWallpaper = wallpaper },
                    onFontSelected = { font -> },
                    onSoundSelected = { sound -> },
                    onUpgradeToPremium = { },
                )
            }
        }
        composable(Destination.Settings.route) { /*Todo Profile Screen content here */ }
    }
}
