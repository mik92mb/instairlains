package com.sky.instairlains.viewModels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.sky.instairlains.BaseViewModel
import com.sky.instairlains.data.network.model.Album
import com.sky.instairlains.data.network.model.Post
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber.i

class DetailViewModel(application: Application) : BaseViewModel(application) {

    val successAlbum = MutableLiveData<List<Album>>()
    val errorAlbum = MutableLiveData<Throwable>()

    val successPost = MutableLiveData<List<Post>>()
    val errorPost = MutableLiveData<Throwable>()


    fun getAlbums(id: Int) {
        i("ID" + id.toString())
        apiClient.getAlbums()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMapIterable {
                i(it.toString())
                it }
            .filter { it.userId == id }
            .toList()
            .subscribe({
                successAlbum.value = it
                i(it.toString())
            }, { errorAlbum.value = it })
            .autoDispose()
    }

    fun getPosts(id: Int) {
        apiClient.getPosts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMapIterable { it }
            .filter { it.userId == id }
            .toList()
            .subscribe({
                successPost.value = it
            }, { errorPost.value = it })
            .autoDispose()
    }
}