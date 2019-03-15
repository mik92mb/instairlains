package com.sky.instairlains

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.sky.instairlains.data.network.PostAPI
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

open class BaseViewModel(application: Application) : AndroidViewModel(application) {

    private val subscription by lazy { CompositeDisposable() }

    protected val apiClient: PostAPI = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(PostAPI::class.java)

    protected val apiClientAirlain: PostAPI = Retrofit.Builder()
        .baseUrl(AIRLAINS_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(PostAPI::class.java)


    override fun onCleared() {
        super.onCleared()
        subscription.clear()
    }


    protected fun Disposable.autoDispose() {
        subscription.add(this)
    }
}
