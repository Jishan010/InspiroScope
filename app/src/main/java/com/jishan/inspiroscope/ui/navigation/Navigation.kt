package com.jishan.inspiroscope.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jishan.inspiroscope.R
import com.jishan.inspiroscope.ui.screen.home.HomeScreen
import com.jishan.inspiroscope.ui.screen.theme.Font
import com.jishan.inspiroscope.ui.screen.theme.Sound
import com.jishan.inspiroscope.ui.screen.theme.ThemeScreen
import com.jishan.inspiroscope.ui.screen.theme.Wallpaper
import com.jishan.inspiroscope.ui.theme.BreeSerifRegular
import com.jishan.inspiroscope.ui.theme.DeliciousHandrawn
import com.jishan.inspiroscope.ui.theme.DynaPuff
import com.jishan.inspiroscope.ui.theme.IndieFlower
import com.jishan.inspiroscope.ui.theme.MarkScriptRegular
import com.jishan.inspiroscope.ui.theme.VtThreeThreeThreeRegular

@Composable
fun MainNavigation(innerPadding: PaddingValues, navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Destination.Home.route,
        Modifier.padding(innerPadding)
    ) {
        composable(Destination.Home.route) { HomeScreen() }
        composable(Destination.Theme.route) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                // HomeScreen()
                val wallpapers = listOf(
                    Wallpaper("Wallpaper 1", Color.Red, R.drawable.first_wallpaper),
                    Wallpaper("Wallpaper 2", Color.Green, R.drawable.second_wallpaper),
                    Wallpaper("Wallpaper 4", Color.Red, R.drawable.fourth_wallpaper),
                    Wallpaper("Wallpaper 6", Color.Blue, R.drawable.sixth_wallpaper),
                    Wallpaper("Wallpaper 7", Color.Red, R.drawable.seventh_wallpaper),
                    Wallpaper("Wallpaper 8", Color.Green, R.drawable.eighth_wallaper),
                    Wallpaper("Wallpaper 9", Color.Blue, R.drawable.ninth_wallpaper)
                )

                val fonts = listOf(
                    Font("Dyna Puff", DynaPuff),
                    Font("Bree Serif", BreeSerifRegular),
                    Font("Delicious Handrawn", DeliciousHandrawn),
                    Font("Indie Flower", IndieFlower),
                    Font("Mark Script", MarkScriptRegular),
                    Font("Vt333", VtThreeThreeThreeRegular)
                )

                val sounds = listOf(
                    Sound("Sound 1", null),
                    Sound("Sound 2", null),
                    Sound("Sound 3", null)
                )

                ThemeScreen(
                    wallpapers = wallpapers,
                    fonts = fonts,
                    sounds = sounds,
                    onWallpaperSelected = { wallpaper -> },
                    onFontSelected = { font -> },
                    onSoundSelected = { sound -> },
                    onUpgradeToPremium = { }
                )
            }
        }
        composable(Destination.Settings.route) { /* Profile Screen content here */ }
    }
}
