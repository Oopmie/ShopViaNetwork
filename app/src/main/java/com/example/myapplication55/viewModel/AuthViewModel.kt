package com.example.myapplication55.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication55.data.AppRepository
import com.example.myapplication55.data.SessionManager
import com.example.network.UserAPI
import com.example.network.model.AuthRequest
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: AppRepository,
    private val sessionManager: SessionManager
) : ViewModel() {
//    fun loginUser(authRequest: AuthRequest){
//        viewModelScope.launch {
//            try {
//                val request = authRequest
//                if(request isSuc)
//            }catch (e: Exception){println("Ошибка")}
//        }
//    }
//    var loginText by mutableStateOf("")
//        private set
//
//    // Флаг ошибки (пустое поле)
//    var isLoginError by mutableStateOf(false)
//        private set
//
//    // Функция обновления текста (вызывается при вводе)
//    fun onLoginChanged(newValue: String) {
//        loginText = newValue
//        // Если пользователь начал вводить текст, убираем красную рамку
//        if (newValue.isNotEmpty()) {
//            isLoginError = false
//        }
//    }
    // Функция нажатия на кнопку
//    fun onLoginClick(onSuccess: () -> Unit) {
//        if (loginText.isEmpty()) {
//            // Если пусто — включаем красный цвет
//            isLoginError = true
//        } else {
//            // Если текст есть — выключаем ошибку и запускаем логику входа
//            isLoginError = false
//            loginUser(loginText, onSuccess)
//        }
//    }
//    fun loginUser(email: String, pass: String, onSuccess: () -> Unit) {
//        viewModelScope.launch {
//            val result = repository.login(AuthRequest(email, pass))
//            result.onSuccess { response ->
//                sessionManager.saveUserId(response.record.id)
//                onSuccess()
//            }.onFailure {
//
//            }
//        }
//    }
}
