package com.example.uikit

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

//разделитель
@Composable
fun SpacerScreen(thickness: Dp){
    HorizontalDivider(modifier = Modifier.fillMaxWidth(), color = Color.Gray, thickness = thickness)
}