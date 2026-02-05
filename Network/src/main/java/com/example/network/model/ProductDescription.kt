package com.example.network.model

import com.google.gson.annotations.SerializedName

data class ProductDescription(
    @SerializedName("id")
    val id: String,
    @SerializedName("collectionId")
    val collectionId: String,
    @SerializedName("collectionName")
    val collectionName: String,
    @SerializedName("created")
    val created: String,
    @SerializedName("updated")
    val updated: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("typeCloses")
    val typeCloses: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("approximateCost")
    val approximateCost: String
)
