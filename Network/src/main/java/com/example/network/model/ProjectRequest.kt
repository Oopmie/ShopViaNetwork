package com.example.network.model

import com.google.gson.annotations.SerializedName

data class ProjectRequest(
    @SerializedName("title")
    val title: String,
    @SerializedName("typeProject")
    val typeProject: String,
    @SerializedName("user_id")
    val userId: String,
    @SerializedName("dateStart")
    val dateStart: String,
    @SerializedName("dateEnd")
    val dateEnd: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("description_source")
    val descriptionSource: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("image")
    val image: String
)

