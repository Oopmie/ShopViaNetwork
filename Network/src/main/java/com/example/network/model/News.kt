package com.example.network.model

import com.google.gson.annotations.SerializedName

data class News(
    @SerializedName("collectionId")
    val collectionId: String,
    @SerializedName("collectionName")
    val collectionName: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("newsImage")
    val newsImage: String,
    @SerializedName("created")
    val created: String,
    @SerializedName("updated")
    val updated: String
)

