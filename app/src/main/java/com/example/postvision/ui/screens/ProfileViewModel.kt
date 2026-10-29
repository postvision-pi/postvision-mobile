
package com.example.postvision.ui.screens

import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModelProvider
import com.example.postvision.ui.model.UpdateUserRequest
import com.example.postvision.ui.repository.UserRepository
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: UserRepository) : ViewModel() {
    var isLoading by mutableStateOf(false)
    var isEditing by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)

    var userId by mutableStateOf("")
    var firstName by mutableStateOf("")
    var lastName by mutableStateOf("")
    var email by mutableStateOf("")
    var gender by mutableStateOf("")
    var phone by mutableStateOf("")
    var cpf by mutableStateOf("")
    var birthDate by mutableStateOf("")
    var password by mutableStateOf("*******")

    private var authToken = ""

    fun loadUserProfile(userEmail: String, token: String) {
        authToken = token
        viewModelScope.launch {
            isLoading = true
            try {
                val response = repository.getUserByEmail(userEmail, token)
                if (response.isSuccessful && response.body() != null) {
                    val user = response.body()!!
                    userId = user.id ?: ""
                    firstName = user.firstName
                    lastName = user.lastName
                    email = user.email
                    gender = user.gender ?: ""
                    phone = user.phone ?: ""
                    cpf = user.cpf ?: ""
                    birthDate = user.birthDate ?: ""
                } else {
                    errorMessage = "Erro ao carregar perfil"
                }
            } catch (e: Exception){
                errorMessage = e.message
            } finally {
                isLoading = false
            }
        }
    }

    fun updateUserProfile() {
        viewModelScope.launch {
            isLoading = true
            try {
                val request = UpdateUserRequest(
                    firstName = firstName,
                    lastName = lastName,
                    email = email,
                    password = if (password.contains("***")) null else password,
                    birthDate = birthDate,
                    cpf = cpf,
                    gender = gender,
                    phone = phone
                )
                val response = repository.updateUser(userId, authToken, request)
                if (response.isSuccessful) {
                    isEditing = false
                } else {
                    errorMessage = "Erro ao atualizar dados"
                }
            } catch (e: Exception) {
                errorMessage = e.message
            } finally {
                isLoading = false
            }
        }
    }
}

class ProfileViewModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProfileViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}