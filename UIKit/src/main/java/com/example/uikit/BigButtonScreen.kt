package com.example.uikit

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.uikit.ui.theme.Accent
import com.example.uikit.ui.theme.AccentInactive

@Composable
fun BigButton(enabled: Boolean, onClick: () -> Unit, text: String) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPresed by interactionSource.collectIsPressedAsState()
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Accent,
            disabledContainerColor = AccentInactive,
            contentColor = Color.White,
            disabledContentColor = Color.White
        ),
        border = BorderStroke(
            width = 2.dp,
            color = if (enabled) Accent else AccentInactive
        )

    ){
        Text(text=text, color = Color.White)
    }
}