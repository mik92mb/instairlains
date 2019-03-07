package com.sky.instairlains.viewModels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.sky.instairlains.BASE_URL
import com.sky.instairlains.BaseViewModel
import com.sky.instairlains.data.network.model.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class HomeViewModel(application: Application) : BaseViewModel(application) {

    val success = MutableLiveData<List<User>>()
    val error = MutableLiveData<Throwable>()

    fun getUsers() {
        apiClient.getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ success.value = it }, { error.value = it })
            .autoDispose()
    }

}