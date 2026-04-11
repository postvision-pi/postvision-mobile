package com.example.postvision

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.auth0.android.jwt.JWT
import com.example.postvision.ui.model.User
import com.example.postvision.ui.network.ApiService
import com.example.postvision.ui.repository.UserRepository
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel: ViewModel(){
    // Estados da UI
    var loggedUser by mutableStateOf<User?>(null)
    var isLoading by mutableStateOf(false)
    var loginError by mutableStateOf<String?>(null)


    // Configuração do Retrofit
    private val api = Retrofit.Builder()
        .baseUrl("https://postvision-api.onrender.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)

    private val repository = UserRepository(api)

    fun performLogin(email: String, pass: String, onNavigate: () -> Unit){
        viewModelScope.launch {
            isLoading = true
            loginError = null
            try{

                val response = repository.login(email, pass)
                if(response.token != null){
                    val jwt = JWT(response.token)
                    val idView = jwt.getClaim("id_usuario").asString()
                    val firstNameView = jwt.getClaim("nome_usuario").asString() ?: "Usuário"
                    val lastNameView = jwt.getClaim("sobrenome_usuario").asString() ?: ""
                    loggedUser = User(
                        id = idView,
                        firstName = firstNameView,
                        lastName = lastNameView,
                        email = email,
                        password = pass
                    )



                    Log.d("LOGIN_TEST", response.token) // Verifique isso no Logcat
                    onNavigate()
                }
            } catch (e: Exception){
                loginError = "Erro: Usuário ou senha inválidos"
            } finally {
                isLoading = false
            }
        }
    }

}