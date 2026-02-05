package com.example.network.model

import com.google.gson.annotations.SerializedName

data class TokenResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("perPage")
    val perPage: Int,
    @SerializedName("totalPages")
    val totalPages: Int,
    @SerializedName("totalItems")
    val totalItems: Int,
    @SerializedName("items")
    val items: List<TokenList>
)

