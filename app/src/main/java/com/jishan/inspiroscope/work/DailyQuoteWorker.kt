package com.jishan.inspiroscope.work

import android.annotation.SuppressLint
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.jishan.domain.entitiy.QuoteEntity
import com.jishan.domain.usecase.GetRandomQuoteUseCase
import com.jishan.inspiroscope.R
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@HiltWorker
class DailyQuoteWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val getRandomQuoteUseCase: GetRandomQuoteUseCase,
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        // Fetch a quote from the backend
        val quoteResult = getRandomQuoteUseCase.invoke()
        quoteResult.onSuccess {
            sendQuoteNotification(it)
        }

        // Indicate whether the work finished successfully
        Result.success()
    }

    @SuppressLint("MissingPermission")
    private fun sendQuoteNotification(quote: QuoteEntity) {
        // Create a notification with the quote content
        val notification = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Daily Quote")
            .setContentText("${quote.quote} - ${quote.author}")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        // Show the notification
        NotificationManagerCompat.from(applicationContext).notify(NOTIFICATION_ID, notification)
    }

    companion object {
        const val CHANNEL_ID = "daily_quote_channel"
        const val NOTIFICATION_ID = 1
    }
}
