package com.sky.instairlains.data.network

import com.sky.instairlains.data.network.model.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface PostAPI {

    @GET("/users")
    fun getUsers(): Observable<List<User>>

    @GET("/posts")
    fun getPosts(): Observable<List<Post>>

    @GET("/albums")
    fun getAlbums(): Observable<List<Album>>


    @GET("/json/airline-tickets.php")
    fun getFly(@Query("from") from: String, @Query("to") to: String): Observable<List<Fly>>

    @GET("/json/airline-tickets-price.php")
    fun getPrice(@Query("flight_number") flight_number: String, @Query("from") from: String, @Query("to") to: String): Observable<Plane>


}