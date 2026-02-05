package com.example.network.model

import com.google.gson.annotations.SerializedName

data class CartRequest(
    @SerializedName("user_id")
    val userId: String,
    @SerializedName("product_id")
    val productId: String,
    @SerializedName("count")
    val count: Int
)
