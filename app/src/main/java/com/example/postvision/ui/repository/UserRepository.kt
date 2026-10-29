package com.example.postvision.ui.repository

import com.example.postvision.ui.model.UpdateUserRequest
import com.example.postvision.ui.network.ApiService
import com.example.postvision.ui.model.User

class UserRepository(private val apiService: ApiService){
    suspend fun getUsers() = apiService.getUsers()
    suspend fun create(user: User) = apiService.createUser(user)
    suspend fun login(email: String, pass: String) =
        apiService.login(mapOf("email" to email, "password" to pass))

    suspend fun getUserByEmail(email: String, token: String) =
        apiService.getUserByEmail(email, "Bearer $token")

    suspend fun updateUser(userId: String, token: String, request: UpdateUserRequest) =
        apiService.updateUser(userId, "Bearer $token", request)
}