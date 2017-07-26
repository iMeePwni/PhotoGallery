package com.imeepwni.android.photogallery.viewmodel

import android.databinding.*
import com.imeepwni.android.photogallery.model.data.*

/**
 * Create by guofeng on 2017/7/25.
 */
class PhotoViewModel(photo: Photo) : BaseObservable() {
    private var photo: Photo = photo

    fun setPhoto(photo: Photo): Unit {
        this.photo = photo
        notifyChange()
    }

    @Bindable
    fun getPhotoId(): String = photo.id

}