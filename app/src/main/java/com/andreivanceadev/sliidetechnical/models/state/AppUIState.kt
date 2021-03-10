package com.andreivanceadev.sliidetechnical.models.state

import com.andreivanceadev.sliidetechnical.models.UserItem

data class AppUIState(
    val loading: Boolean = false,
    val users: List<UserItem>? = null,
    val error: String? = null
)
