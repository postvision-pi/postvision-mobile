package com.example.postvision.ui.repository

import com.example.postvision.ui.network.ApiService
import com.example.postvision.ui.model.User

class UserRepository(private val apiService: ApiService){
    suspend fun getUsers() = apiService.getUsers()
    suspend fun create(user: User) = apiService.createUser(user)

    suspend fun login(email: String, pass: String) =
        apiService.login(mapOf("email" to email, "password" to pass))
}