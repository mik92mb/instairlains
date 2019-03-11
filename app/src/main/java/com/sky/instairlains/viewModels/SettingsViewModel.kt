package com.sky.instairlains.viewModels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.sky.instairlains.BaseViewModel
import com.sky.instairlains.adapters.AirlainsAdapter
import com.sky.instairlains.data.network.model.Fly
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.operators.observable.ObservableFromIterable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber.i


class SettingsViewModel(application: Application) : BaseViewModel(application) {

    val success = MutableLiveData<List<Fly>>()
    val error = MutableLiveData<Throwable>()


    fun getFly(adapter: AirlainsAdapter) {
        apiClientAirlain.getFly("CHE", "DEL")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    adapter.clear()
                    adapter.addAll(it)
                    //  success.value = it
                    i("ALL" + it.toString())
                },
                { error.value = it })
            .autoDispose()
        apiClientAirlain.getFly("CHE", "DEL")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap {
                ObservableFromIterable(it)
            }.flatMap {
                getPriceObservable(it, adapter)
            }
            .toList()
            .subscribe(
                {
                    adapter.clear()
                    adapter.addAll(it)
                    //  success.value = it
                    i("ALL" + it.toString())
                },
                { error.value = it })
            .autoDispose()
    }


    private fun getPriceObservable(fly: Fly, adapter: AirlainsAdapter): Observable<Fly>? {
        return apiClientAirlain
            .getPrice(fly.flight_number, "DEL", "CHE")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                i("FLY: " + fly.airline.name + " " + fly.flight_number + " " + fly.airline.plane)
                i("PLANE: " + it.toString())
                fly.airline.plane = it
                val position = adapter.flyList.indexOf(fly)
                if (position != -1) {
                    adapter.flyList[position] = fly
                    i("ARRIVO")
                    adapter.notifyItemChanged(position)
                }
                fly
            }
    }


}