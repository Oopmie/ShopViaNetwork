package com.example.network.model

import com.google.gson.annotations.SerializedName

data class AuthResponse (
    @SerializedName("record")
    val record: User,
    @SerializedName("token")
    val token: String
)
