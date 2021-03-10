package com.andreivanceadev.sliidetechnical.actions

import com.andreivanceadev.sliidetechnical.models.state.AppState

class UserAddAction : AppAction {
    override fun updateState(appState: AppState): AppState =
        appState.copy(
            appUIState = appState.appUIState.copy(
                loading = false,
                error = null
            )
        )
}