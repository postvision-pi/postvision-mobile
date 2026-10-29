package com.example.postvision.ui.network

import com.example.postvision.ui.model.LoginResponse
import com.example.postvision.ui.model.UpdateUserRequest
import com.example.postvision.ui.model.User
import okhttp3.Credentials
import retrofit2.http.*
import retrofit2.Response

interface ApiService{
    @GET("user")
    suspend fun getUsers(): List<User>

    @POST("user")
    suspend fun createUser(@Body user: User): User

    @POST("auth")
    suspend fun login(@Body credentials: Map<String, String>): LoginResponse

    @GET("user/{email}")
    suspend fun getUserByEmail(
        @Path("email") email: String,
        @Header("Authorization") token: String
    ): Response<User>

    @PUT("user/{userId}")
    suspend fun updateUser(
        @Path("userId") userId: String,
        @Header("Authorization") token: String,
        @Body request: UpdateUserRequest
    ): Response<User>
}