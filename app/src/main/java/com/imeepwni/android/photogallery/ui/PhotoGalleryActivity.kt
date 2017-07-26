package com.imeepwni.android.photogallery.ui

import android.support.v4.app.*
import com.imeepwni.android.photogallery.app.*

class PhotoGalleryActivity : SingleFragmentActivity() {
    override fun getFragment(): Fragment {
        return PhotoGalleryFragment.newInstance()
    }
}
