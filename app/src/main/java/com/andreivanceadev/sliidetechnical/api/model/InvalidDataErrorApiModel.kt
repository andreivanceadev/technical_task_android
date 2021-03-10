package com.andreivanceadev.sliidetechnical.api.model

import com.google.gson.annotations.SerializedName

data class InvalidDataErrorApiModel(
    @SerializedName("field") val field: String,
    @SerializedName("message") val message: String
)