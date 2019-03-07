package com.sky.instairlains.viewModels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.sky.instairlains.AIRLAINS_URL
import com.sky.instairlains.BaseViewModel
import com.sky.instairlains.data.network.model.Fly
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FlyViewModel(application: Application) : BaseViewModel(application) {

    val success = MutableLiveData<List<Fly>>()
    val error = MutableLiveData<Throwable>()


    fun getFly() {
        apiClientAirlain.getFly()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ success.value = it }, { error.value = it })
            .autoDispose()
    }
}