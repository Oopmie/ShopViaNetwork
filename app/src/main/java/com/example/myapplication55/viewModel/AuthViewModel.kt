package com.example.myapplication55.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication55.data.SessionManager
import com.example.network.UserAPI
import com.example.network.model.AuthRequest
import com.example.network.model.Registration
import kotlinx.coroutines.launch

class AuthViewModel(val api: UserAPI, val sessionManager: SessionManager) : ViewModel() {
    // Поля для временного хранения данных с экрана CreateAccount
    var name = "";
    var surname = "";
    var sName = "";
    var birth = "";
    var gender = "";
    var email = ""

    var userName by mutableStateOf("")
    var userEmail by mutableStateOf("")

    init {
        loadUserData()
    }

    fun loadUserData() {
        userName = sessionManager.getFirstName()
        userEmail = sessionManager.getEmail()
    }
    fun isUserLoggedIn(): Boolean {
        return sessionManager.getToken() != null
    }
    fun registerAndSaveAll(pass: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                // ШАГ 1: Регистрация
                val regRes = api.createUser(Registration(email, pass, pass))
                if (regRes.isSuccessful) {
                    Log.d("API_STEP", "1. Регистрация успешна")
                    sessionManager.saveEmail(email)

                    // ШАГ 2: Логин
                    val authRes = api.authUser(AuthRequest(identity = email, password = pass))
                    if (authRes.isSuccessful) {
                        Log.d("API_STEP", "2. Логин успешен")
                        val token = authRes.body()?.token ?: ""
                        val userId = authRes.body()?.record?.id ?: ""
                        sessionManager.saveToken(token)
                        sessionManager.saveUserId(userId)

                        val updatedUser = authRes.body()!!.record.copy(
                            firstName = name,
                            lastName = surname,
                            secondName = sName,
                            dateBirthday = birth,
                            gender = gender
                        )

                        // ШАГ 3: Обновление профиля
                        val updateRes = api.updateUser("Bearer $token", userId, updatedUser)
                        if (updateRes.isSuccessful) {
                            Log.d("API_STEP", "3. Профиль обновлен. Переходим!")
                            onSuccess()
                        } else {
                            Log.e("API_ERR", "Ошибка Шаг 3: ${updateRes.code()} - ${updateRes.errorBody()?.string()}")
                        }
                    } else {
                        Log.e("API_ERR", "Ошибка Шаг 2: ${authRes.code()}")
                    }
                } else {
                    Log.e("API_ERR", "Ошибка Шаг 1: ${regRes.code()} - ${regRes.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("API_ERR", "Критическая ошибка: ${e.message}")
            }
        }
    }
    fun signIn(email: String, pass: String, onHome: () -> Unit, onRegister: () -> Unit) {
        viewModelScope.launch {
            try {
                val response = api.authUser(AuthRequest(identity = email, password = pass))

                if (response.isSuccessful) {
                    // Аккаунт есть! Сохраняем данные и идем на Главную
                    sessionManager.saveEmail(email)
                    val authData = response.body()
                    authData?.token?.let { sessionManager.saveToken(it) }
                    authData?.record?.id?.let { sessionManager.saveUserId(it) }
                    onHome()
                } else {
                    // Аккаунта нет или неверный пароль -> на создание профиля
                    // Сохраняем почту и пароль, чтобы не вводить их заново
                    this@AuthViewModel.email = email
                    onRegister()
                }
            } catch (e: Exception) {
                // Ошибка сети
                Log.e("AUTH", "Network error: ${e.message}")
            }
        }
    }
    fun logout(onSuccess: () -> Unit) {
        viewModelScope.launch {
            val idToken = sessionManager.getUserId() ?: ""
            val token = "Bearer ${sessionManager.getToken()}"

            try {
                api.deleteUser(idToken, token)
                sessionManager.clear()
                onSuccess()
            } catch (e: Exception) {
                sessionManager.clear()
                onSuccess()
                Log.e("LOGOUT", "Ошибка при удалении сессии: ${e.message}")
            }
        }
    }
}
