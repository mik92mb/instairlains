package com.sky.instairlains.activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sky.instairlains.BaseActivity
import com.sky.instairlains.R
import com.sky.instairlains.adapters.GalleryAdapter
import com.sky.instairlains.viewModels.GalleryViewModel
import kotlinx.android.synthetic.main.activity_gallery.*

class GalleryActivity : BaseActivity() {

    private val galleryAdapter = GalleryAdapter(this)
    private lateinit var viewModelGallery: GalleryViewModel

    companion object {
        const val BUNDLE_ID = "ID"
        const val BUNDLE_NAME_ALBUM = "NAME_ALBUM"

        fun start(activity: AppCompatActivity, id: Int, title: String) {
            val intent = Intent(activity, GalleryActivity::class.java)
            intent.putExtra(BUNDLE_ID, id)
            intent.putExtra(BUNDLE_NAME_ALBUM, title)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)
        setRecyclerView()
        val id = intent.getIntExtra(BUNDLE_ID, 0)
        val nameAlbum = intent.getSerializableExtra(BUNDLE_NAME_ALBUM)
        setToolbar(nameAlbum.toString())
        viewModelGallery = ViewModelProviders.of(this).get(GalleryViewModel::class.java)
        setData(id)
    }


    private fun setToolbar(title: String) {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = title
    }

    private fun setData(id: Int) {
        viewModelGallery.getPhotos(id)

        viewModelGallery.success.observe(this, Observer {
            progressBarPhoto.visibility = View.GONE
            galleryAdapter.clear()
            galleryAdapter.addAll(it)
        })
        viewModelGallery.error.observe(this, Observer {
            progressBarPhoto.visibility = View.GONE

        })
    }

    private fun setRecyclerView() {
        recyclerPhoto.layoutManager = GridLayoutManager(this, 3, RecyclerView.VERTICAL, false)
        recyclerPhoto.adapter = galleryAdapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
