package com.andreivanceadev.sliidetechnical.extensions

import com.andreivanceadev.sliidetechnical.actions.AppAction
import com.andreivanceadev.sliidetechnical.models.AddUserEvent
import com.andreivanceadev.sliidetechnical.models.AppEvent
import com.andreivanceadev.sliidetechnical.models.DeleteUserEvent
import com.andreivanceadev.sliidetechnical.models.LoadUsersEvent
import com.andreivanceadev.sliidetechnical.models.state.AppState
import com.andreivanceadev.sliidetechnical.models.state.AppUIState
import com.andreivanceadev.sliidetechnical.usecase.addUser
import com.andreivanceadev.sliidetechnical.usecase.deleteUser
import com.andreivanceadev.sliidetechnical.usecase.loadUsers
import io.reactivex.rxjava3.core.Observable

internal fun Observable<AppEvent>.eventToActions(appState: () -> AppState?): Observable<out AppAction> =
    publish {
        Observable.merge(
            arrayListOf(
                ofType(LoadUsersEvent::class.java).loadUsers(),
                ofType(DeleteUserEvent::class.java).deleteUser(),
                ofType(AddUserEvent::class.java).addUser()
            )
        )
    }

internal fun Observable<out AppAction>.actionToViewState(): Observable<AppState> {
    return scan(AppState()) { state, action ->
        action.updateState(state)
    }.skip(1).distinctUntilChanged()
}

internal fun Observable<AppState>.mapToUiState(): Observable<AppUIState> =
    map { checkoutState -> checkoutState.appUIState }
        .distinctUntilChanged()
