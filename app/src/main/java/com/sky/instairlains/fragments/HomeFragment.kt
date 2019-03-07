package com.sky.instairlains.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sky.instairlains.IMAGE_URL
import com.sky.instairlains.R
import com.sky.instairlains.adapters.UserAdapter
import com.sky.instairlains.viewModels.MainViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_home.*
import timber.log.Timber.i


class HomeFragment : Fragment() {


    private lateinit var userAdapter : UserAdapter
    private lateinit var mainViewModel : MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        userAdapter = UserAdapter(inflater.context)
       // setRecyclerView(inflater.context)
       // setImageView()
       // callGetUSer()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        i("SONO IN onViewCreated")
        setRecyclerView(context!!.applicationContext)
        setImageView()
        callGetUSer()
    }


    private fun setImageView() {
        Picasso.get()
            .load(IMAGE_URL)
            .into(image_header)
    }


    private fun callGetUSer() {
        i("SONO IN callGetUSer")
        mainViewModel.getUsers()
        mainViewModel.success.observe(this, Observer {
            userAdapter.clearAll()
            userAdapter.addUsers(it)
        })
        mainViewModel.error.observe(this, Observer {

        })
    }

    private fun setRecyclerView(context: Context) {
        i("SONO IN setRecyclerView")
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        recyclerView.adapter = userAdapter
    }
}