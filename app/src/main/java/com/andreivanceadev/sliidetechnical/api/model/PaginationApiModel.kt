package com.andreivanceadev.sliidetechnical.api.model

import com.google.gson.annotations.SerializedName

data class PaginationApiModel(
    @SerializedName("pages") val pages: Int
)