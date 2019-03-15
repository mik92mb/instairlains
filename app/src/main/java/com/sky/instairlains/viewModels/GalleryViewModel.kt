package com.sky.instairlains.viewModels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.sky.instairlains.BaseViewModel
import com.sky.instairlains.data.network.model.Photo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GalleryViewModel(application: Application) : BaseViewModel(application) {

    val success = MutableLiveData<List<Photo>>()
    val error = MutableLiveData<Throwable>()


    fun getPhotos(id: Int) {
        apiClient.getPhotos()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMapIterable { it }
            .filter { it.albumId == id }
            .toList()
            .subscribe({
                success.value = it
            }, { error.value = it })
            .autoDispose()
    }

}