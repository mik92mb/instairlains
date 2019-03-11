package com.sky.instairlains.data.network.model

import com.google.gson.annotations.SerializedName


data class Fly(

    @SerializedName("from")
    val from: String,
    @SerializedName("to")
    val to: String,
    @SerializedName("flight_number")
    val flight_number: String,
    @SerializedName("departure")
    val departure: String,
    @SerializedName("arrival")
    val arrival: String,
    @SerializedName("duration")
    val duration: String,
    @SerializedName("stops")
    val stops: String,
    @SerializedName("airline")
    val airline: AirLine


)

data class AirLine(

    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("logo")
    val logo: String,

    var plane: Plane
)

data class Plane(

    @SerializedName("price")
    val price: Int,
    @SerializedName("seats")
    val seats: Int,
    @SerializedName("currency")
    val currency: String


)