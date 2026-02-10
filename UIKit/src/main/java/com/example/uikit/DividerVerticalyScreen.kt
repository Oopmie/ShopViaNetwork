package com.example.uikit

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

@Composable
fun VerticalSpacer(thickness: Dp){
    VerticalDivider(modifier = Modifier.fillMaxHeight(), color = Color.Gray, thickness = thickness)
}