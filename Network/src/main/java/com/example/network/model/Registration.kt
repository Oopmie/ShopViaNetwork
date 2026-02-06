package com.example.network.model

import com.google.gson.annotations.SerializedName

data class Registration(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("passwordConfirm")
    val passwordConfirm: String
)
