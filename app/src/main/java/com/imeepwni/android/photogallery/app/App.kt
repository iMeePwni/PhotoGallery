package com.imeepwni.android.photogallery.app

import android.app.*
import com.orhanobut.logger.*
import okhttp3.*
import okhttp3.logging.*
import java.util.concurrent.*

/**
 * Create by guofeng on 2017/7/20.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        app = this
        Logger.addLogAdapter(object : AndroidLogAdapter() {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return true
            }
        })
        client = OkHttpClient().newBuilder()
                .cache(Cache(app.cacheDir, 5 * 1024 * 1024))
                .addInterceptor(HttpLoggingInterceptor(HttpLoggingInterceptor
                        .Logger { message -> Logger.i(message) })
                        .apply { level = HttpLoggingInterceptor.Level.BODY })
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build()!!
    }

    companion object {
        private lateinit var app: App
        private lateinit var client: OkHttpClient

        fun getInstance() = app
        fun getOkHttpClient() = client
    }
}