package com.example.network.model

import com.google.gson.annotations.SerializedName

data class OrderResponse(
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
    @SerializedName("user_id")
    val userId: String,
    @SerializedName("product_id")
    val productId: String,
    @SerializedName("count")
    val count: Int
)
