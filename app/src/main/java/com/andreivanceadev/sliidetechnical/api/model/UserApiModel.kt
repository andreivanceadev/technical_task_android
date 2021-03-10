package com.andreivanceadev.sliidetechnical.api.model

import com.google.gson.annotations.SerializedName

data class UserApiModel(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("created_at") val createdAt: String
)
