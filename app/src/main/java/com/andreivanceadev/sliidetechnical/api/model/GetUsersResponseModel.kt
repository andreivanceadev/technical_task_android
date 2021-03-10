package com.andreivanceadev.sliidetechnical.api.model

import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName

data class GetUsersResponseModel(
    @SerializedName("code") val code: Int,
    @SerializedName("meta") val meta: MetaApiModel?,
    @SerializedName("data") val data: JsonElement?,
    var users: List<UserApiModel>?,
    var error: String?,
    var fieldErrors: List<InvalidDataErrorApiModel>
)
