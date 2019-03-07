package com.sky.instairlains.data.network

import com.sky.instairlains.data.network.model.Fly
import com.sky.instairlains.data.network.model.User
import io.reactivex.Observable
import retrofit2.http.GET

interface PostAPI {

    @GET("/users")
    fun getUsers () : Observable<List<User>>

    @GET("/json/airline-tickets.php?from=DEL&to=CHE")
    fun getFly() : Observable<List<Fly>>
}