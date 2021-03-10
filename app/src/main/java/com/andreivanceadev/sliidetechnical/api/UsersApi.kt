package com.andreivanceadev.sliidetechnical.api

import com.andreivanceadev.sliidetechnical.api.model.CreateUserApiModel
import com.andreivanceadev.sliidetechnical.api.model.GetUsersResponseModel
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.*

interface UsersApi {

    @GET("users")
    fun getUsers(@Query("page") page: Int = 1): Observable<GetUsersResponseModel>

    @POST("users")
    fun addUser(@Body user: CreateUserApiModel): Observable<GetUsersResponseModel>

    @DELETE("users/{id}")
    fun deleteUser(@Path("id") userId: Int): Observable<GetUsersResponseModel>

}