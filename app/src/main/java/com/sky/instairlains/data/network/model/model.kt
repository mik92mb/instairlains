package com.sky.instairlains.data.network.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

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
): Serializable {
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
    @SerializedName("zipcode")
    val zipCode: String
) : Serializable {

    override fun toString(): String {
        return street.plus(" ").plus(suite).plus(",").plus(city).plus("-").plus(zipCode)
    }
}

data class Company(
    @SerializedName("name")
    val name: String,
    @SerializedName("catchPhrase")
    val catchPhrase: String,
    @SerializedName("bs")
    val bs: String
) : Serializable

data class Post(
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("body")
    val body: String
)

data class Album(
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String
)

data class Photo(
    @SerializedName("albumId")
    val albumId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String
)