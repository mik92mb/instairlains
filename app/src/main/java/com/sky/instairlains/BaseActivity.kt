package com.sky.instairlains.activity

import android.support.v7.app.AppCompatActivity
import com.sky.instairlains.BASE_URL
import com.sky.instairlains.data.network.PostAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class BaseActivity : AppCompatActivity(){


    private val apiCLient = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PostAPI::class.java)
}