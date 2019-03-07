package com.sky.instairlains.data.network.model

import com.google.gson.annotations.SerializedName

data class User(

    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("username")
    val userName: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("address")
    val address: Address,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("website")
    val website: String,
    @SerializedName("company")
    val company: Company
) {
    override fun toString(): String {
        return this.name.plus(" ").plus(this.userName)
    }
}

data class Address(
    @SerializedName("street")
    val street: String,
    @SerializedName("suite")
    val suite: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("zipCode")
    val zipCode: String
)

data class Company(
    @SerializedName("name")
    val name: String,
    @SerializedName("catchPhrase")
    val catchPhrase: String,
    @SerializedName("bs")
    val bs: String
)

data class Post(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("body")
    val body: String
)

data class Album(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String
)

data class Photo(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String
)