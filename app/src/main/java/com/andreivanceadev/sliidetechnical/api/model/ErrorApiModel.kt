package com.andreivanceadev.sliidetechnical.api.model

import com.google.gson.annotations.SerializedName

data class ErrorApiModel(
    @SerializedName("message") val message: String
)