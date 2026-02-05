package com.example.network.model

import com.google.gson.annotations.SerializedName

data class TokenList(
    @SerializedName("collectionId")
    val collectionId: String,
    @SerializedName("collectionName")
    val collectionName: String,
    @SerializedName("created")
    val created: String,
    @SerializedName("updated")
    val updated: String,
    @SerializedName("collectionRef")
    val collectionRef: String,
    @SerializedName("fingerprint")
    val fingerprint: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("recordRef")
    val recordRef: String
)
