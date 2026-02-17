package com.example.myapplication55.presentation.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.sp
import com.example.uikit.ui.theme.Accent
import com.example.uikit.ui.theme.AccentInactive
import com.example.uikit.ui.theme.White

@Composable
fun LoadingScreen() {
    Box(modifier = Modifier.fillMaxSize().background(brush = Brush.linearGradient(
        colors = listOf( AccentInactive, Accent, AccentInactive
        ), start = Offset(0f,Float.POSITIVE_INFINITY),
        end = Offset(Float.POSITIVE_INFINITY,0f)
    )), contentAlignment = Alignment.Center) {
        Text("Matule", fontSize = 40.sp, color = White)
    }
}