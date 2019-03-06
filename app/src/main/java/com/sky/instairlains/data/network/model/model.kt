package com.sky.instairlains.data.network.model

import com.google.gson.annotations.SerializedName


data class User(

    @SerializedName ("id")
    val id: Int,
    @SerializedName ("name")
    val name: String,
    @SerializedName ("userName")
    val userName: String,
    @SerializedName ("email")
    val email: String,
    @SerializedName ("address")
    val address: Address,
    @SerializedName ("phone")
    val phone: String,
    @SerializedName ("website")
    val website: String,
    @SerializedName ("company")
    val company: Company
)


data class Address(
    @SerializedName ("street")
    val street: String,
    @SerializedName ("suite")
    val suite: String,
    @SerializedName ("city")
    val city: String,
    @SerializedName ("zipCode")
    val zipCode: String
)

data class Company(
    @SerializedName ("name")
    val name: String,
    @SerializedName ("catchPhrase")
    val catchPhrase: String,
    @SerializedName ("bs")
    val bs: String
)