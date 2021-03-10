package com.andreivanceadev.sliidetechnical.models.state

import com.andreivanceadev.sliidetechnical.api.model.UserApiModel

data class AppNetworkState(
    val users: List<UserApiModel>?
)