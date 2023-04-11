package com.jishan.analytics

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics as fba

class FirebaseAnalytics(context: Context) : Analytics {
    @SuppressLint("MissingPermission")
    private val firebaseAnalytics = fba.getInstance(context)

    override fun pageView(name: String, params: Map<String, String>) {
        // Use the standard SCREEN_VIEW event with SCREEN_NAME parameter
        val bundle = params.toBundle()
        bundle.putString(fba.Param.SCREEN_NAME, name)
        firebaseAnalytics.logEvent(fba.Event.SCREEN_VIEW, bundle)
    }

    override fun event(name: String, params: Map<String, String>) {
        firebaseAnalytics.logEvent(name, params.toBundle())
    }

    fun Map<String, String>.toBundle() = Bundle().apply {
        forEach { key, value -> this.putString(key, value) }
    }
}
