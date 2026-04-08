package com.example.postvision.ui.network

import com.example.postvision.ui.model.LoginResponse
import com.example.postvision.ui.model.User
import okhttp3.Credentials
import retrofit2.http.*

interface ApiService{
    @GET("user")
    suspend fun getUsers(): List<User>

    @POST("user")
    suspend fun createUser(@Body user: User): User

    @POST("auth")
    suspend fun login(@Body credentials: Map<String, String>): LoginResponse
}