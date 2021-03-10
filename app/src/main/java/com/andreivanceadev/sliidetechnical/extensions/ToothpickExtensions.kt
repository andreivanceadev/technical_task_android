package com.andreivanceadev.sliidetechnical.extensions

import toothpick.Scope
import toothpick.Toothpick

internal fun Scope.inject(any: Any) = Toothpick.inject(any, this)

internal inline fun <reified T> Scope.getInstance(name: String? = null): T = this.getInstance(T::class.java, name)