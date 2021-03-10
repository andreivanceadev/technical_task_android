package com.andreivanceadev.sliidetechnical.actions

import com.andreivanceadev.sliidetechnical.models.state.AppState

interface AppAction {
    fun updateState(appState: AppState): AppState
}
