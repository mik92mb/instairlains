package com.sky.instairlains.viewModels

import android.app.Application
import android.util.Log.i
import androidx.lifecycle.MutableLiveData
import com.sky.instairlains.BaseViewModel
import com.sky.instairlains.FROM
import com.sky.instairlains.TO
import com.sky.instairlains.adapters.AirlainsAdapter
import com.sky.instairlains.data.network.model.Fly
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.operators.observable.ObservableFromIterable
import io.reactivex.observables.ConnectableObservable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber.i


class SettingsViewModel(application: Application) : BaseViewModel(application) {

    val success = MutableLiveData<List<Fly>>()
    val error = MutableLiveData<Throwable>()
    private val observableFly: ConnectableObservable<List<Fly>> = getFly()!!.replay()

    private fun getFly(): Observable<List<Fly>>? {
        i("getFly")
        return apiClientAirlain.getFly(FROM, TO)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


    fun getAll(adapter: AirlainsAdapter) {
        i("getAll")
        observableFly
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                success.value = it
            }, {
                i("ERRORE: %s", it.printStackTrace())
            })
            .autoDispose()

        observableFly
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap {
                ObservableFromIterable(it)
            }
            .flatMap {
                getPriceObservable(it)
            }
            .subscribe({
                val position = adapter.flyList.indexOf(it)
                i("POSIZIONE: $position")
                if (position != -1) {
                    adapter.flyList[position] = it
                    adapter.notifyItemChanged(position)
                }
            }, {
                i("ERRORE: %s", it.printStackTrace())
            })
            .autoDispose()
        observableFly.connect()
    }

    private fun getPriceObservable(fly: Fly): Observable<Fly>? {
        return apiClientAirlain
            .getTickets(fly.flight_number, FROM, TO)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                i("FLY: " + fly.airline.name + " " + fly.flight_number + " " + fly.airline.ticket)
                i("TICKET: " + it.toString())
                fly.airline.ticket = it
                /*   val position = adapter.flyList.indexOf(fly)
                   if (position != -1) {
                       adapter.flyList[position] = fly
                       adapter.notifyItemChanged(position)
                   }*/
                fly
            }
    }


}