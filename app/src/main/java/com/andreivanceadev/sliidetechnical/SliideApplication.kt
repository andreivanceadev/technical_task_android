package com.andreivanceadev.sliidetechnical

import android.app.Application
import com.andreivanceadev.sliidetechnical.di.modules.GeneralModule
import com.andreivanceadev.sliidetechnical.di.modules.NetworkModule
import toothpick.Scope
import toothpick.Toothpick

class SliideApplication : Application() {

    private lateinit var appScope: Scope

    override fun onCreate() {
        super.onCreate()
        appScope = Toothpick.openScope(this)
        appScope.installModules(
            GeneralModule(this),
            NetworkModule()
        )
    }

}