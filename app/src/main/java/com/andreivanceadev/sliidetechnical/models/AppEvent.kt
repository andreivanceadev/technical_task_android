package com.andreivanceadev.sliidetechnical.models

import android.app.Application

sealed class AppEvent

data class LoadUsersEvent(val application: Application) : AppEvent()
data class DeleteUserEvent(val application: Application, val user: UserItem) : AppEvent()
data class AddUserEvent(val application: Application, val user: UserItem) : AppEvent()
