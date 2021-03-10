package com.andreivanceadev.sliidetechnical.api

import com.andreivanceadev.sliidetechnical.api.model.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers.io
import toothpick.InjectConstructor

@InjectConstructor
class UsersApiService constructor(private val usersApi: UsersApi) {

    fun getUsers(): Observable<Result<List<UserApiModel>>> {
        return usersApi.getUsers()
            .flatMap { usersApi.getUsers(it.meta!!.pagination.pages) }
            .map { resultCodeMapper(it) }
            .map {
                if (it.error.isNullOrEmpty()) {
                    Result.success(it.users!!)
                } else {
                    Result.failure(Throwable(it.error))
                }
            }
            .subscribeOn(io())
    }

    fun deleteUser(id: Int): Observable<Result<String?>> {
        return usersApi.deleteUser(id)
            .map { resultCodeMapper(it) }
            .map {
                if (it.error.isNullOrEmpty()) Result.success<String?>("")
                else Result.failure(Throwable(it.error))
            }
            .subscribeOn(io())
    }

    private fun resultCodeMapper(it: GetUsersResponseModel): GetUsersResponseModel {
        when (it.code) {
            200 -> {
                it.users =
                    Gson().fromJson(it.data, object : TypeToken<ArrayList<UserApiModel>>() {}.type)
            }
            in 201 until 400-> {

            }
            in 400 until 422 -> {
                it.error = Gson().fromJson(it.data, ErrorApiModel::class.java).message
            }
            422 -> {
                it.fieldErrors = Gson().fromJson(
                    it.data,
                    object : TypeToken<ArrayList<InvalidDataErrorApiModel>>() {}.type
                )
            }
            else -> it.error = Gson().fromJson(it.data, ErrorApiModel::class.java).message

        }
        return it
    }

    fun addUser(user: CreateUserApiModel): Observable<Result<String?>> {
        return usersApi.addUser(user)
            .map { resultCodeMapper(it) }
            .map {
                if (it.fieldErrors.isNullOrEmpty()) Result.success<String?>("")
                else Result.failure(Throwable())
            }
            .subscribeOn(io())
    }

}
