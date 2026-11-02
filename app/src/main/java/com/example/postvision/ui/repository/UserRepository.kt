package com.example.postvision.ui.repository

import com.example.postvision.ui.model.UpdateUserRequest
import com.example.postvision.ui.network.ApiService
import com.example.postvision.ui.model.User
import com.example.postvision.ui.model.SessionPayload

class UserRepository(private val apiService: ApiService){
    suspend fun getUsers() = apiService.getUsers()
    suspend fun create(user: User) = apiService.createUser(user)
    suspend fun login(email: String, pass: String) =
        apiService.login(mapOf("email" to email, "password" to pass))

    suspend fun getUserByEmail(email: String, token: String) =
        apiService.getUserByEmail(email, "Bearer $token")

    suspend fun updateUser(userId: String, token: String, request: UpdateUserRequest) =
        apiService.updateUser(userId, "Bearer $token", request)

    suspend fun getUserReports(userId: String, token: String) =
        apiService.getUserReports(userId, "Bearer $token")

    suspend fun saveTrainingSession(token: String, payload: SessionPayload): Boolean {
        return try {
            val response = apiService.saveTrainingSession("Bearer $token", payload)
            if (response.isSuccessful) {
                android.util.Log.d("API_SAVE", "Sessão salva com sucesso.")
                true
            } else {
                android.util.Log.e("API_SAVE", "Erro HTTP ${response.code()}: ${response.errorBody()?.string()}")
                false
            }
        } catch (e: Exception) {
            android.util.Log.e("API_SAVE", "Erro de rede/código: ${e.message}")
            false
        }
    }
}