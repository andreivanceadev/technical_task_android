package com.andreivanceadev.sliidetechnical.models.state

data class AppState(
    // UI State
    // It is recoverable from network + business-cat
    val appUIState: AppUIState = AppUIState(),

    // Network Content
    // It is recoverable from network
    val appNetworkState: AppNetworkState? = null
)

