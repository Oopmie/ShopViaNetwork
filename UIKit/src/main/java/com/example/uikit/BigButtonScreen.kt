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
import androidx.compose.ui.unit.dp
import com.example.uikit.ui.theme.Accent
import com.example.uikit.ui.theme.AccentInactive
import com.example.uikit.ui.theme.White

//кнопка на странице создания проекта, при нажатии создает проект, если поля заполнены
@Composable
fun BigButton(enabled: Boolean,
              onClick: () -> Unit,
              text: String) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    Button(
        onClick = onClick,
        enabled = enabled,
        interactionSource = interactionSource,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isPressed) White else (Accent),
            contentColor = if (isPressed) Accent else (White),
            disabledContainerColor = AccentInactive,
            disabledContentColor = White
        ),
        border = BorderStroke(
            2.dp, color = if (!enabled) AccentInactive
            else Accent
        )
    ) {
        Text(text = text)
    }
}

//git branch -m master sprint1
//Git add .
//Git commit -m "spr1"
//Git push origin sprint1
//
//git checkout -b main
//git push -u origin main
//
//git checkout -b sprint2
//git add .
//Git commit "spr2"
//git push -u origin sprint2
//git checkout main
//git merge sprint2
//git push origin main