package com.imeepwni.android.photogallery.model.data

/**
 * Create by guofeng on 2017/7/25.
 */
data class Photo(
        var id: String,
        var owner: String,
        var secret: String,
        var server: String,
        var farm: Int,
        var title: String,
        var ispublic: Int,
        var isfriend: Int,
        var isfamily: Int,
        var url_s: String,
        var height_s: String,
        var width_s: String)