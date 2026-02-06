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
//кнопка на странице создания проекта, при нажатии создает проект, если поля заполнены
@Composable
fun BigButton(enabled: Boolean, onClick: () -> Unit, text: String) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val containerColor = when {
        !enabled -> AccentInactive
        isPressed -> Color.White
        else -> Accent
    }

    val contentColor = if (isPressed && enabled) Accent else Color.White
    val borderColor = if (enabled) Accent else AccentInactive

    Button(
        onClick = onClick,
        enabled = enabled,
        interactionSource = interactionSource,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = AccentInactive,
            disabledContentColor = Color.White
        ),
        border = BorderStroke(2.dp, borderColor)
    ) {
        Text(text = text)
    }
}