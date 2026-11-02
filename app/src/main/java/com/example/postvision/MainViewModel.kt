package com.example.postvision

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.auth0.android.jwt.JWT
import com.example.postvision.ui.model.User
import com.example.postvision.ui.network.RetrofitClient
import com.example.postvision.ui.repository.UserRepository
import com.google.gson.JsonParser
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


class MainViewModel: ViewModel(){
    var authToken by mutableStateOf("")
    // Estados da UI
    var loggedUser by mutableStateOf<User?>(null)
    var isLoading by mutableStateOf(false)
    var loginError by mutableStateOf<String?>(null)

    // Estados do cadastro
    var isRegistering by mutableStateOf(false)
    var registerError by mutableStateOf<String?>(null)


    // Configuração do Retrofit
    private val api = RetrofitClient.apiService

    private val repository = UserRepository(api)

    // Faz o login e preenche authToken/loggedUser. Retorna false se a API não devolveu token.
    private suspend fun authenticate(email: String, pass: String): Boolean {
        val response = repository.login(email, pass)
        val token = response.token ?: return false

        authToken = token

        val jwt = JWT(token)
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

        Log.d("LOGIN_TEST", token) // Verifique isso no Logcat
        return true
    }

    fun performLogin(email: String, pass: String, onNavigate: () -> Unit){
        viewModelScope.launch {
            isLoading = true
            loginError = null
            try{
                if (authenticate(email, pass)) {
                    onNavigate()
                }
            } catch (e: Exception){
                Log.e("LOGIN_ERRO", "Motivo da falha: ${e.message}")
                loginError = "Erro: Usuário ou senha inválidos"
            } finally {
                isLoading = false
            }
        }
    }

    fun clearRegisterError() {
        registerError = null
    }

    // Cadastra o usuário e, se der certo, já faz o login e chama onSuccess
    fun performRegister(user: User, onSuccess: () -> Unit){
        viewModelScope.launch {
            isRegistering = true
            registerError = null
            try {
                repository.create(user)
                Log.d("REGISTER", "Usuário cadastrado com sucesso")
            } catch (e: HttpException) {
                val body = e.response()?.errorBody()?.string()
                Log.e("REGISTER", "Erro HTTP ${e.code()}: $body")
                registerError = extractErrorMessage(body)
                    ?: "Não foi possível cadastrar (erro ${e.code()})."
                isRegistering = false
                return@launch
            } catch (e: IOException) {
                Log.e("REGISTER", "Erro de rede: ${e.message}")
                registerError = "Sem conexão com o servidor. Tente novamente."
                isRegistering = false
                return@launch
            } catch (e: Exception) {
                Log.e("REGISTER", "Erro inesperado: ${e.message}")
                registerError = "Erro inesperado ao cadastrar."
                isRegistering = false
                return@launch
            }

            // Cadastro ok: entra direto com as mesmas credenciais
            try {
                if (authenticate(user.email, user.password ?: "")) {
                    onSuccess()
                } else {
                    registerError = "Cadastro realizado! Faça login para continuar."
                }
            } catch (e: Exception) {
                Log.e("REGISTER", "Cadastro ok, mas login falhou: ${e.message}")
                registerError = "Cadastro realizado! Faça login para continuar."
            } finally {
                isRegistering = false
            }
        }
    }

    // Tenta ler "message" ou "error" do corpo de erro da API
    private fun extractErrorMessage(body: String?): String? {
        if (body.isNullOrBlank()) return null
        return try {
            val obj = JsonParser.parseString(body).asJsonObject
            val element = obj.get("message") ?: obj.get("error")
            if (element != null && element.isJsonPrimitive) element.asString else null
        } catch (e: Exception) {
            null
        }
    }

}
