package com.example.myapplication55.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication55.data.SessionManager
import com.example.network.UserAPI
import com.example.network.model.AuthRequest
import com.example.network.model.ProjectResponse
import com.example.network.model.Registration
import kotlinx.coroutines.launch

class AuthViewModel(val api: UserAPI, val sessionManager: SessionManager) : ViewModel() {
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
    fun isPasswordValid(password: String): Boolean {
        val hasUpperCase = password.any { it.isUpperCase() }
        val hasLowerCase = password.any { it.isLowerCase() }
        val hasDigit = password.any { it.isDigit() }
        val hasSpecialChar = password.any { !it.isLetterOrDigit() }
        val isLongEnough = password.length >= 8
        return hasUpperCase && hasLowerCase && hasDigit && hasSpecialChar && isLongEnough
    }
    fun registerAndSaveAll(pass: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                val regRes = api.createUser(Registration(email, pass, pass))
                if (regRes.isSuccessful) {
                    sessionManager.saveEmail(email)
                    val authRes = api.authUser(AuthRequest(identity = email, password = pass))
                    if (authRes.isSuccessful) {
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
                        val updateRes = api.updateUser("Bearer $token", userId, updatedUser)
                        if (updateRes.isSuccessful) { onSuccess() }
                    }
                }
            } catch (e: Exception){}
        }
    }
    fun signIn(email: String, pass: String, onHome: () -> Unit, onRegister: () -> Unit) {
        viewModelScope.launch {
            try {
                val response = api.authUser(AuthRequest(identity = email, password = pass))
                if (response.isSuccessful) {
                    sessionManager.saveEmail(email)
                    val authData = response.body()
                    authData?.token?.let { sessionManager.saveToken(it) }
                    authData?.record?.id?.let { sessionManager.saveUserId(it) }
                    onHome()
                } else {
                    onRegister()
                }
            } catch (e: Exception) {}
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
            }
        }
    }
}
