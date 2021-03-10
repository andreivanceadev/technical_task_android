package com.andreivanceadev.sliidetechnical.actions

import android.app.Application
import com.andreivanceadev.sliidetechnical.api.model.UserApiModel
import com.andreivanceadev.sliidetechnical.converter.UsersConverter
import com.andreivanceadev.sliidetechnical.models.state.AppState
import toothpick.Toothpick
import javax.inject.Inject

class UpdateUsersAction(
    application: Application,
    private val users: List<UserApiModel>
) : AppAction {

    @Inject
    lateinit var usersConverter: UsersConverter

    init {
        Toothpick.openScope(application).inject(this)
    }

    override fun updateState(appState: AppState): AppState =
        appState.copy(
            appUIState = appState.appUIState.copy(
                loading = false,
                users = usersConverter.convert(users),
                error = null
            )
        )
}
