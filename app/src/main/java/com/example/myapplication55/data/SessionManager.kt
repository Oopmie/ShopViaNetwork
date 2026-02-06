package com.example.myapplication55.data

import android.content.Context
import androidx.core.content.edit

class SessionManager(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("user_session", Context.MODE_PRIVATE)
    fun saveUserId(userId: String) {
        sharedPreferences.edit { putString("USER_ID", userId) }
    }
    fun getUserId(): String? {
        return sharedPreferences.getString("USER_ID", null)
    }
    fun clearData() {
        sharedPreferences.edit {clear()}
    }
}
