package com.andreivanceadev.sliidetechnical.repository

import toothpick.InjectConstructor

@InjectConstructor
class TimeRepositoryImpl : TimeRepository {

    override fun getCurrentTimeMs() =
        System.currentTimeMillis()

}