package com.imeepwni.android.photogallery.ui


import android.databinding.*
import android.os.*
import android.support.v4.app.*
import android.support.v7.widget.*
import android.view.*
import android.widget.*
import com.imeepwni.android.photogallery.*
import com.imeepwni.android.photogallery.databinding.*
import com.imeepwni.android.photogallery.model.data.*
import com.imeepwni.android.photogallery.model.repository.*
import com.imeepwni.android.photogallery.viewmodel.*
import com.squareup.picasso.*


class PhotoGalleryFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentPhotoGalleryBinding>(inflater, R.layout.fragment_photo_gallery, container, false)
        with(binding.recyclerView) {
            layoutManager = GridLayoutManager(activity, 3, LinearLayout.VERTICAL, false)
            adapter = Adapter(FlickrRepository.getRecentPhotos())
        }
        return binding.root
    }

    inner class Adapter(val list: List<Photo>) : RecyclerView.Adapter<ViewHolder>() {
        override fun getItemCount(): Int = list.size

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
            val binding = DataBindingUtil.inflate<ListItemPhotoBinding>(LayoutInflater.from(activity), R.layout.list_item_photo, parent, false)
            return ViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(list[position])
    }

    inner class ViewHolder(val binding: ListItemPhotoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(photo: Photo): Unit {
            with(binding) {
                if (viewmodel==null)
                    viewmodel = PhotoViewModel(photo)
                else {
                    viewmodel?.setPhoto(photo)
                    executePendingBindings()
                }
                Picasso.with(activity).load(photo.url_s).into(imageView)
            }
        }
    }

    companion object {
        fun newInstance(): PhotoGalleryFragment {
            val fragment = PhotoGalleryFragment()
            return fragment
        }
    }

}
