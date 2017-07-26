package com.imeepwni.android.photogallery.model.net

import com.imeepwni.android.photogallery.app.*
import com.imeepwni.android.photogallery.model.data.*
import retrofit2.*
import retrofit2.converter.gson.*
import retrofit2.http.*

/**
 * Create by guofeng on 2017/7/26.
 */
object Flickr {
    private val BASE_URL = "https://api.flickr.com/"
    private val APP_KEY = "f399c8ed9c0d1337c196dd1c7101ea7c"

    private val METHOD_GET_RECENT = "flickr.photos.getRecent"
    private val METHOD_SEARCH = "flickr.photos.search"

    private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(App.getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()!!

    val flickrService = retrofit.create(FlickrService::class.java)!!

    interface FlickrService {
        @GET("services/rest")
        fun recentPhoto(@Query("method") path: String = METHOD_GET_RECENT,
                        @Query("api_key") apiKey: String = APP_KEY,
                        @Query("format") format: String = "json",
                        @Query("nojsoncallback") nojsoncallback: Int = 1,
                        @Query("extras") extras: String = "url_s"): Call<RecentPhotos>

        @GET("services/rest")
        fun searchPhoto(@Query("method") path: String = METHOD_SEARCH,
                        @Query("api_key") apiKey: String = APP_KEY,
                        @Query("format") format: String = "json",
                        @Query("nojsoncallback") nojsoncallback: Int = 1,
                        @Query("text") text: String)
    }
}