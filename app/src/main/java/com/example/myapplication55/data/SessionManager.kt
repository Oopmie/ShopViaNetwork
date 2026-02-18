package com.example.myapplication55.data

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

class SessionManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("user_session", Context.MODE_PRIVATE)
    private val gson = Gson()
    fun saveToken(token: String) {
        sharedPreferences.edit().putString("auth_token", token).apply() }
    fun getToken(): String? {
        return sharedPreferences.getString("auth_token", null) }
    fun saveUserId(userId: String) {
        sharedPreferences.edit().putString("user_id", userId).apply() }
    fun getUserId(): String? {
        return sharedPreferences.getString("user_id", null) }
    fun clear() { sharedPreferences.edit().clear().apply() }
    fun getFirstName(): String = sharedPreferences.getString("f_name",
        "Имя не указано") ?: ""
    fun getEmail(): String = sharedPreferences.getString("user_email",
        "email@example.com") ?: ""
    fun saveEmail(email: String) {
        sharedPreferences.edit().putString("user_email", email).apply() }
}
