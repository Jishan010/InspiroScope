package com.jishan.analytics

interface Analytics {
    fun pageView(name: String, params: Map<String, String> = emptyMap())

    fun event(name: String, params: Map<String, String> = emptyMap())
}
