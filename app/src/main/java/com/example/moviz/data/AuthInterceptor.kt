package com.example.moviz.data

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder()

        requestBuilder.addHeader("Authorization", "Bearer 'token'")

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}