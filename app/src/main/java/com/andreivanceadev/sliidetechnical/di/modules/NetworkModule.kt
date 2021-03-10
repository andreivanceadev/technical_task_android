package com.andreivanceadev.sliidetechnical.di.modules

import com.andreivanceadev.sliidetechnical.api.UsersApiProvider
import com.andreivanceadev.sliidetechnical.api.UsersApi
import toothpick.config.Module

class NetworkModule: Module() {

    init {
        bind(UsersApi::class.java).toProviderInstance(UsersApiProvider())
    }
}