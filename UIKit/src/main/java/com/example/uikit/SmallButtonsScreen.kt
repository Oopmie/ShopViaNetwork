package com.example.uikit

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.uikit.ui.theme.Accent
import com.example.uikit.ui.theme.Caption

//кнопки для карточек товаров,
//при нажатии отправляет товар в корзину.
@Composable
fun SmallButton(
    isAdded: Boolean,
    onToggle: (Boolean) -> Unit,
    textDelete: String,
    textAdd: String,
    modifier: Modifier = Modifier
) {
    val containerColor = if (isAdded) Color.White else Accent
    val contentColor = if (isAdded) Accent else Color.White

    Button(
        onClick = { onToggle(!isAdded) },
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        border = BorderStroke(1.dp, color = Accent),
    ) {
        Text(
            text = if (isAdded) textDelete else textAdd,
            style = Caption.bodyMedium
        )
    }
}

