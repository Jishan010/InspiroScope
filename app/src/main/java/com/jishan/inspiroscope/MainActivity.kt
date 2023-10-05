package com.jishan.inspiroscope

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.jishan.inspiroscope.ui.screen.home.HomeScreen
import com.jishan.inspiroscope.ui.screen.theme.entities.Font
import com.jishan.inspiroscope.ui.screen.theme.entities.Sound
import com.jishan.inspiroscope.ui.screen.theme.entities.Wallpaper
import com.jishan.inspiroscope.ui.theme.BreeSerifRegular
import com.jishan.inspiroscope.ui.theme.DeliciousHandrawn
import com.jishan.inspiroscope.ui.theme.DynaPuff
import com.jishan.inspiroscope.ui.theme.IndieFlower
import com.jishan.inspiroscope.ui.theme.InspiroScopeTheme
import com.jishan.inspiroscope.ui.theme.MarkScriptRegular
import com.jishan.inspiroscope.ui.theme.VtThreeThreeThreeRegular
import com.jishan.inspiroscope.work.DailyQuoteWorker
import com.jishan.inspiroscope.work.DailyQuoteWorker.Companion.CHANNEL_ID
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createNotificationChannel()
        setContent {
            InspiroScopeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    HomeScreen()
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
                        Sound("Sound 1", R.drawable.first_sound),
                        Sound("Sound 2", R.drawable.second_sound),
                        Sound("Sound 3", R.drawable.third_sound),
                        Sound("Sound 4", R.drawable.fourth_sound),
                        Sound("Sound 5", R.drawable.fifth_sound),
                        Sound("Sound 6", R.drawable.sixth_sound),
                        Sound("Sound 7", R.drawable.seventh_sound),
                    )

                    /*ThemeScreen(
                        wallpapers = wallpapers,
                        fonts = fonts,
                        sounds = sounds,
                        onWallpaperSelected = { wallpaper -> },
                        onFontSelected = { font -> },
                        onSoundSelected = { sound -> },
                        onUpgradeToPremium = { }
                    )*/
                }
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
            window.insetsController?.let { controller ->
                controller.systemBarsBehavior =
                    WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                controller.hide(WindowInsets.Type.statusBars())
            }
        } else {
            @Suppress("DEPRECATION") window.decorView.systemUiVisibility =
                (View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        }

        scheduleDailyQuoteWorker()
    }

    private fun scheduleDailyQuoteWorker() {
        val workRequest = PeriodicWorkRequestBuilder<DailyQuoteWorker>(24, TimeUnit.HOURS).build()

        WorkManager.getInstance(this)
            .enqueueUniquePeriodicWork("dailyQuote", ExistingPeriodicWorkPolicy.KEEP, workRequest)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val NOTIFICATION_CHANNEL_NAME = "dailyQuote"
            val channel =
                NotificationChannel(CHANNEL_ID, NOTIFICATION_CHANNEL_NAME, importance).apply {
                    description = "Daily inspirational quotes"
                }
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }
}
