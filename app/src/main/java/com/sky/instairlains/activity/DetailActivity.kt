package com.sky.instairlains.activity


import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sky.instairlains.BaseActivity
import com.sky.instairlains.R
import com.sky.instairlains.adapters.AlbumAdapter
import com.sky.instairlains.adapters.PostAdapter
import com.sky.instairlains.data.network.model.User
import com.sky.instairlains.viewModels.DetailViewModel
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : BaseActivity() {

    private val albumAdapter = AlbumAdapter(this)
    private val postAdapter = PostAdapter(this)
    private lateinit var detailViewModel: DetailViewModel

    companion object {
        const val USER = "USER"
        fun start(activity: AppCompatActivity, imageView: ImageView, user: User) {
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(USER, user)
            val options = ActivityOptions.makeSceneTransitionAnimation(activity, imageView, "transition")
            activity.startActivity(intent, options.toBundle())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setRecyclers()
        val user = intent.getSerializableExtra(USER) as? User
        detailViewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        setToolbar(user.toString())
        setCardView(user!!)
        callgetAlbums(user.id)
        callgetPosts(user.id)
    }

    private fun setCardView(user: User) {
        emailUser.text = user.email
        webUser.text = user.website
        phoneUser.text = user.phone
    }

    private fun callgetAlbums(id: Int) {
        detailViewModel.getAlbums(id)
        detailViewModel.successAlbum.observe(this, Observer {
            albumAdapter.clearAll()
            albumAdapter.addAlbum(it)
        })
        detailViewModel.errorAlbum.observe(this, Observer {
        })
    }

    private fun callgetPosts(id: Int) {
        detailViewModel.getPosts(id)
        detailViewModel.successPost.observe(this, Observer {
            postAdapter.clearAll()
            postAdapter.addPosts(it)
        })
        detailViewModel.errorPost.observe(this, Observer {
        })
    }

    private fun setRecyclers() {
        recyclerAlbum.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        recyclerAlbum.adapter = albumAdapter
        recyclerPost.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerPost.adapter = postAdapter
    }

    private fun setToolbar(title: String) {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = title
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
