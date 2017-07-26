package com.imeepwni.android.photogallery.model.data

/**
 * Create by guofeng on 2017/7/26.
 */
data class Photos(
        var page: Int,
        var pages: Int,
        var perpage: Int,
        var total: Int,
        var photo: List<Photo>,
        var stat: String
)