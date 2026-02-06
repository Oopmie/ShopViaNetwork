package com.example.myapplication55.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication55.data.AppRepository
import com.example.myapplication55.data.SessionManager
import com.example.network.model.AuthRequest
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: AppRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    fun loginUser(email: String, pass: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            val result = repository.login(AuthRequest(email, pass))
            result.onSuccess { response ->
                sessionManager.saveUserId(response.record.id)
                onSuccess()
            }.onFailure {

            }
        }
    }
}
