package com.sky.instairlains.data.network

import com.sky.instairlains.data.network.model.User
import io.reactivex.Observable
import retrofit2.http.GET

interface PostAPI {

    @GET("/users")
    fun getUsers () : Observable<List<User>>
}