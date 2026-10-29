package com.example.postvision.ui.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("_id")
    val id: String? = null,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String? = null,
    val birthDate: String? = null,
    val cpf: String? = null,
    val gender: String? = null,
    val phone: String? = null
)

data class UpdateUserRequest(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String? = null,
    val birthDate: String,
    val cpf: String,
    val gender: String,
    val phone: String
)

data class LoginResponse(
    val user: User?,
    val token: String? = null,
    val message: String? = null,
)

