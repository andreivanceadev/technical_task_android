package com.andreivanceadev.sliidetechnical.api

import javax.inject.Provider

class UsersApiProvider: Provider<UsersApi> {
    override fun get(): UsersApi = RetrofitInstance().retrofit.create(UsersApi::class.java)
}