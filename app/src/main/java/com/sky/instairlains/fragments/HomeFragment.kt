package com.sky.instairlains.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sky.instairlains.IMAGE_URL
import com.sky.instairlains.R
import com.sky.instairlains.adapters.UserAdapter
import com.sky.instairlains.viewModels.HomeViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_home.*
import timber.log.Timber.i


class HomeFragment : Fragment() {


    private lateinit var userAdapter: UserAdapter
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        i("SONO IN onViewCreated")
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        userAdapter = UserAdapter(view.context)
        setRecyclerView(view.context)
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
        homeViewModel.getUsers()
        homeViewModel.success.observe(this, Observer {
            userAdapter.clearAll()
            userAdapter.addUsers(it)
        })
        homeViewModel.error.observe(this, Observer {
            Toast.makeText(context, "ERROR! Users not found!", Toast.LENGTH_SHORT).show()
        })
    }

    private fun setRecyclerView(context: Context) {
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        recyclerView.adapter = userAdapter
    }
}