package com.example.network.model

import com.google.gson.annotations.SerializedName

data class AuthRequest(
    @SerializedName("identity")
    val identity: String,
    @SerializedName("password")
    val password: String,
)

