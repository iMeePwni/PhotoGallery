package com.imeepwni.android.photogallery.ui


import android.content.*
import android.databinding.*
import android.os.*
import android.support.v4.app.*
import android.support.v7.widget.*
import android.support.v7.widget.SearchView
import android.view.*
import android.view.inputmethod.*
import android.widget.*
import com.imeepwni.android.photogallery.R
import com.imeepwni.android.photogallery.databinding.*
import com.imeepwni.android.photogallery.model.data.*
import com.imeepwni.android.photogallery.model.repository.*
import com.imeepwni.android.photogallery.viewmodel.*
import com.orhanobut.logger.*
import com.squareup.picasso.*


class PhotoGalleryFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentPhotoGalleryBinding>(inflater, R.layout.fragment_photo_gallery, container, false)
        with(binding.recyclerView) {
            layoutManager = GridLayoutManager(activity, 3, LinearLayout.VERTICAL, false)
            adapter = Adapter(FlickrRepository.getRecentPhotos())
        }
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_photo_gallery, menu)

        val systemService = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val searchView: SearchView = menu.findItem(R.id.menu_item_search).actionView as SearchView
        searchView.setQuery("hi", false)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                systemService.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS)
                Logger.i(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Logger.i(newText)
                return true
            }
        })
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
