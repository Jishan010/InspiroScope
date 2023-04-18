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
import androidx.compose.ui.text.font.FontFamily
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.jishan.inspiroscope.screen.theme.Font
import com.jishan.inspiroscope.screen.theme.Sound
import com.jishan.inspiroscope.screen.theme.ThemeScreen
import com.jishan.inspiroscope.screen.theme.Wallpaper
import com.jishan.inspiroscope.ui.theme.InspiroScopeTheme
import com.jishan.inspiroscope.work.DailyQuoteWorker
import com.jishan.inspiroscope.work.DailyQuoteWorker.Companion.CHANNEL_ID
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InspiroScopeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    // HomeScreen()
                    val wallpapers = listOf(
                        Wallpaper("Wallpaper 1", Color.Red),
                        Wallpaper("Wallpaper 2", Color.Green),
                        Wallpaper("Wallpaper 3", Color.Blue),
                        Wallpaper("Wallpaper 1", Color.Red),
                        Wallpaper("Wallpaper 2", Color.Green),
                        Wallpaper("Wallpaper 3", Color.Blue)
                    )

                    val fonts = listOf(
                        Font("Font 1", FontFamily.Serif),
                        Font("Font 2", FontFamily.SansSerif),
                        Font("Font 3", FontFamily.Monospace),
                        Font("Font 1", FontFamily.Serif),
                        Font("Font 2", FontFamily.SansSerif),
                        Font("Font 3", FontFamily.Monospace)
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

        createNotificationChannel()
        scheduleDailyQuoteWorker()
    }

    private fun scheduleDailyQuoteWorker() {
        val workRequest = PeriodicWorkRequestBuilder<DailyQuoteWorker>(24, TimeUnit.HOURS)
            .build()

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
