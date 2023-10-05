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
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.jishan.inspiroscope.ui.navigation.BottomNavigationBar
import com.jishan.inspiroscope.ui.navigation.MainNavigation
import com.jishan.inspiroscope.ui.screen.theme.ThemeScreen
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
                MainApp()
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


    @Composable
    fun MainApp() {
        val navController = rememberNavController()
        Scaffold(bottomBar = {
            BottomNavigationBar(navController)
        }) { innerPadding ->
            MainNavigation(innerPadding, navController)
        }
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
