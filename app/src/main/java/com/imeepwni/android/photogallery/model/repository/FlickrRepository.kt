package com.imeepwni.android.photogallery.model.repository

import com.google.gson.*
import com.imeepwni.android.photogallery.app.*
import com.imeepwni.android.photogallery.model.data.*
import com.imeepwni.android.photogallery.model.net.*
import com.orhanobut.logger.*
import retrofit2.*

/**
 * Create by guofeng on 2017/7/25.
 */
object FlickrRepository {

    val TAG = javaClass.simpleName!!

    fun getRecentPhotos(): List<Photo> {
        return getLocalPhotos()
    }

    private fun getLocalPhotos(): List<Photo> {
        val string = String(App.getInstance().assets.open("photos.json").readBytes())
        val recentPhotos = Gson().fromJson(string, RecentPhotos::class.java)
        Logger.i(recentPhotos.toString())
        getRecentPhotosFromNet()
        return recentPhotos.photos.photo
    }

    private fun getRecentPhotosFromNet() {
        val call = Flickr.flickrService.recentPhoto()
        call.enqueue(object : Callback<RecentPhotos> {
            override fun onResponse(call: Call<RecentPhotos>?, response: Response<RecentPhotos>?) {
//                recentPhotos = response?.body()
                Logger.t(TAG).i(response.toString())
            }

            override fun onFailure(call: Call<RecentPhotos>?, t: Throwable?) {
//                recentPhotos = null
                Logger.t(TAG).i("fail")
            }
        })
    }

}