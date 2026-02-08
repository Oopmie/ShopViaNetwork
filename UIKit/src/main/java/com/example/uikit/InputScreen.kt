package com.example.uikit

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue

//вводы текста для регистрации
@Composable
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    isError: Boolean = false, // Передаем true, если кнопка нажата, а текста нет
    errorText: String = "Введите ваш текст",
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    // Логика цвета границ:
    // 1. Если ошибка -> Красный
    // 2. Если нажат (фокус) -> Синий
    // 3. Если есть текст -> Темно-серый
    // 4. По умолчанию -> Светло-серый
    val borderColor = when {
        isError -> Color.Red
        isFocused -> Color(0xFF007AFF) // Твой синий (Accent)
        value.isNotEmpty() -> Color.DarkGray
        else -> Color.LightGray
    }

    Column (modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = placeholder, color = Color.Gray) },
            isError = isError,
            interactionSource = interactionSource,
            shape = RoundedCornerShape(12.dp),
            // Настройка цветов через Colors
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF007AFF),
                unfocusedBorderColor = borderColor, // Используем нашу логику
                errorBorderColor = Color.Red,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
            ),
            singleLine = true
        )

        // Показ текста ошибки под полем
        if (isError) {
            Text(
                text = errorText,
                color = Color.Red,
                style = TextStyle(fontSize = 12.sp),
                modifier = Modifier.padding(start = 8.dp, top = 4.dp)
            )
        }
    }
}
