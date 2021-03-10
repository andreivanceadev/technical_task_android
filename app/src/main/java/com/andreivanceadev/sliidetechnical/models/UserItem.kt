package com.andreivanceadev.sliidetechnical.models

data class UserItem(
    val id: Int,
    val name: String,
    val email: String,
    val createdAt: String,
    val gender: String = "",
    val status: String = "",
)
