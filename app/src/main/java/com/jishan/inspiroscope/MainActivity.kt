package com.jishan.inspiroscope

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.jishan.inspiroscope.ui.navigation.BottomNavigationBar
import com.jishan.inspiroscope.ui.navigation.MainNavigation
import com.jishan.inspiroscope.ui.theme.InspiroScopeTheme
import com.jishan.inspiroscope.work.DailyQuoteWorker
import com.jishan.inspiroscope.work.DailyQuoteWorker.Companion.CHANNEL_ID
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // code retrieve the current registration token
        FirebaseMessaging.getInstance().token.addOnCompleteListener(
            OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w("FCM", "Fetching FCM registration token failed", task.exception)
                    return@OnCompleteListener
                }

                // Get new FCM registration token
                val token = task.result

                // Log and toast
                Log.d("FCM", token.toString())
                Toast.makeText(baseContext, token.toString(), Toast.LENGTH_SHORT).show()
            },
        )

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
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility
                =
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
