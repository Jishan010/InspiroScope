package com.jishan.analytics

import android.util.Log

class LogAnalytics : Analytics {
    override fun pageView(name: String, params: Map<String, String>) {
        Log.d(LogAnalytics::class.java.simpleName, "PageView: $name")
        logParams(params)
    }

    override fun event(name: String, params: Map<String, String>) {
        Log.d(LogAnalytics::class.java.simpleName, "Event: $name")
        logParams(params)
    }

    private fun logParams(params: Map<String, String>) {
        val paramLog = params.keys.joinToString("\n") { key ->
            "$key: ${params[key]}"
        }

        if (paramLog.isNotBlank()) {
            Log.d(LogAnalytics::class.java.simpleName, "Params: $paramLog")
        }
    }
}
