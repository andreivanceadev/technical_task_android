package com.andreivanceadev.sliidetechnical.usecase

import com.andreivanceadev.sliidetechnical.actions.*
import com.andreivanceadev.sliidetechnical.api.UsersApiService
import com.andreivanceadev.sliidetechnical.converter.UserApiModelConverter
import com.andreivanceadev.sliidetechnical.extensions.getInstance
import com.andreivanceadev.sliidetechnical.models.AddUserEvent
import com.andreivanceadev.sliidetechnical.models.DeleteUserEvent
import com.andreivanceadev.sliidetechnical.models.LoadUsersEvent
import io.reactivex.rxjava3.core.Observable
import toothpick.Toothpick

internal fun Observable<LoadUsersEvent>.loadUsers(): Observable<out AppAction> =
    switchMap {
        val scope = Toothpick.openScope(it.application)
        val usersApiService: UsersApiService = scope.getInstance()

        return@switchMap usersApiService.getUsers()
            .map { users ->
                if (users.isSuccess) {
                    UpdateUsersAction(it.application, users.getOrNull()!!)
                } else {
                    ErrorAction(users.exceptionOrNull()!!.message)
                }
            }
            .cast(AppAction::class.java)
            .startWithItem(LoadingAction())
    }

internal fun Observable<DeleteUserEvent>.deleteUser(): Observable<out AppAction> =
    switchMap {
        val scope = Toothpick.openScope(it.application)
        val usersApiService: UsersApiService = scope.getInstance()

        return@switchMap usersApiService.deleteUser(it.user.id)
            .map { result ->
                if (result.isSuccess) {
                    UserDeletedAction(it.user.id)
                } else {
                    ErrorAction(result.exceptionOrNull()?.message)
                }

            }
            .cast(AppAction::class.java)
            .startWithItem(LoadingAction())
    }

internal fun Observable<AddUserEvent>.addUser(): Observable<out AppAction> =
    switchMap {
        val scope = Toothpick.openScope(it.application)
        val usersApiService: UsersApiService = scope.getInstance()
        val userApiModelConverter: UserApiModelConverter = scope.getInstance()

        return@switchMap usersApiService.addUser(userApiModelConverter.convert(it.user))
            .map { result ->
                if (result.isSuccess) {
                    UserAddAction()
                } else {
                    ErrorAction(result.exceptionOrNull()?.message)
                }

            }
            .cast(AppAction::class.java)
            .startWithItem(LoadingAction())

    }