package com.andreivanceadev.sliidetechnical.api.model

import com.google.gson.annotations.SerializedName

data class MetaApiModel(
    @SerializedName("pagination") val pagination: PaginationApiModel
)