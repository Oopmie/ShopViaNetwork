package com.example.network.model

import com.google.gson.annotations.SerializedName

data class SearchList(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("typeCloses")
    val typeCloses: String,
    @SerializedName("type")
    val type: String
)