package com.example.network.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("collectionId")
    val collectionId: String,
    @SerializedName("collectionName")
    val collectionName: String,
    @SerializedName("created")
    val created: String,
    @SerializedName("emailVisibility")
    val emailVisibility: Boolean=true,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("secondName")
    val secondName: String,
    @SerializedName("updated")
    val updated: String,
    @SerializedName("verified")
    val verified: Boolean,
    @SerializedName("dateBirthday")
    val dateBirthday: String,
    @SerializedName("gender")
    val gender: String,
)
