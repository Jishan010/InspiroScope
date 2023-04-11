package com.jishan.analytics

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.runs
import io.mockk.verify
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowLog
import com.google.firebase.analytics.FirebaseAnalytics as fba

@Config(sdk = [30])
@RunWith(RobolectricTestRunner::class)
class AnalyticsTest {
    val context = ApplicationProvider.getApplicationContext<Context>()

    @Test
    fun LogAnalytics_expectAnalyticsLogged() {
        val analytics = LogAnalytics()

        ShadowLog.clear()
        analytics.pageView("test", mapOf("Title" to "test page"))
        "test".checkPageView(title = "test page")

        ShadowLog.clear()
        analytics.event("test", mapOf("param1" to "a"))
        "test".checkEvent("param1: a")

        ShadowLog.clear()
        analytics.event("test", mapOf("param1" to "a", "param2" to "b"))
        "test".checkEvent("param1: a", "param2: b")
    }

    @Test
    fun FirebaseAnalytics_expectAnalyticsLogged() {
        mockkStatic(fba::class)
        val fbAnalytics = mockk<fba>()
        every { fba.getInstance(any()) } returns fbAnalytics
        every { fbAnalytics.logEvent(any(), any()) } just runs

        val analytics = FirebaseAnalytics(context)

        analytics.pageView("test", mapOf("Title" to "test page"))
        verify { fbAnalytics.logEvent(any(), any()) }

        analytics.event("test", mapOf("param1" to "a"))
        verify(atLeast = 2) { fbAnalytics.logEvent(any(), any()) }
    }

    fun String.checkPageView(title: String = this, params: String = "") {
        val logs = ShadowLog.getLogsForTag("LogAnalytics")
        Assert.assertEquals("PageView: $this", logs[0].msg)

        if (params.isEmpty()) {
            Assert.assertEquals("Params: Title: $title", logs[1].msg)
        } else {
            Assert.assertEquals("Params: Title: $title\n$params", logs[1].msg)
        }
    }

    fun String.checkEvent(param: String = "", param2: String = "") {
        val logs = ShadowLog.getLogsForTag("LogAnalytics")
        Assert.assertEquals("Event: $this", logs[0].msg)

        if (param.isNotEmpty()) {
            if (param2.isEmpty()) {
                Assert.assertEquals("Params: $param", logs[1].msg)
            } else {
                Assert.assertEquals("Params: $param\n$param2", logs[1].msg)
            }
        }
    }
}
