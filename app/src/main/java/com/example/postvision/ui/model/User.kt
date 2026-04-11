package com.example.postvision.ui.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("_id")
    val id: String? = null,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String
)

data class LoginResponse(
    val user: User?,
    val token: String? = null,
    val message: String? = null,
)

