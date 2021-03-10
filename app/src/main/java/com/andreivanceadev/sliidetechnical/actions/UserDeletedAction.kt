package com.andreivanceadev.sliidetechnical.actions

import com.andreivanceadev.sliidetechnical.models.state.AppState

class UserDeletedAction(private val userId: Int) : AppAction {

    override fun updateState(appState: AppState): AppState =
        appState.copy(
            appUIState = appState.appUIState.copy(
                loading = false,
                users = appState.appUIState.users?.filter { it.id != userId },
                error = null
            )
        )
}
