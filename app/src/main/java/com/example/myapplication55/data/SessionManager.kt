package com.example.myapplication55.data

import android.content.Context
import android.content.SharedPreferences
import com.example.network.model.ProjectResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SessionManager(context: Context) {
    // Создаем хранилище в памяти телефона
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("user_session", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun saveProjects(projects: List<ProjectResponse>) {
        val json = gson.toJson(projects)
        sharedPreferences.edit().putString("local_projects", json).apply()
    }

    fun getProjects(): List<ProjectResponse> {
        val json = sharedPreferences.getString("local_projects", null) ?: return emptyList()
        val type = object : TypeToken<List<ProjectResponse>>() {}.type
        return gson.fromJson(json, type)
    }
    // Функция сохранения токена
    fun saveToken(token: String) {
        sharedPreferences.edit().putString("auth_token", token).apply()
    }

    // Функция получения токена
    fun getToken(): String? {
        return sharedPreferences.getString("auth_token", null)
    }

    // Функция сохранения ID пользователя
    fun saveUserId(userId: String) {
        sharedPreferences.edit().putString("user_id", userId).apply()
    }

    // Функция получения ID
    fun getUserId(): String? {
        return sharedPreferences.getString("user_id", null)
    }

    // Функция очистки (для выхода из аккаунта)
    fun clear() {
        sharedPreferences.edit().clear().apply()
    }
    fun getFirstName(): String = sharedPreferences.getString("f_name", "Имя не указано") ?: ""
    fun getEmail(): String = sharedPreferences.getString("user_email", "email@example.com") ?: ""

    // Сохранить email при регистрации
    fun saveEmail(email: String) {
        sharedPreferences.edit().putString("user_email", email).apply()
    }

}
