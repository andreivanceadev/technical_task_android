package com.andreivanceadev.sliidetechnical.di.modules

import android.app.Application
import com.andreivanceadev.sliidetechnical.repository.TimeRepository
import com.andreivanceadev.sliidetechnical.repository.TimeRepositoryImpl
import toothpick.config.Module

class GeneralModule(application: Application) : Module() {

    init {
        bind(Application::class.java).toInstance(application)
        bind(TimeRepository::class.java).to(TimeRepositoryImpl::class.java)
    }
}