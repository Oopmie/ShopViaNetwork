package com.example.uikit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import com.example.uikit.ui.theme.Error
import com.example.uikit.ui.theme.InputBg

//вводы текста для регистрации
@Composable
fun AppTextField(
    value: String,
    onValueChange: (String)-> Unit,
    placeholder: String,
    isError: Boolean = false,
    errorText: String,
    modifier: Modifier = Modifier
) {
    val isFocused by mutableStateOf(false)
    val borderColor = when {
        isError -> Error
        isFocused -> Color(0xFF007AFF)
        value.isNotEmpty()-> Color(0xFFB8C1CC)
        else -> Color.LightGray
    }

    Column (modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            value=value,
            onValueChange=onValueChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {Text(text = placeholder)},
            isError = isError,
            shape = RoundedCornerShape(10.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF007AFF),
                unfocusedBorderColor = borderColor,
                errorBorderColor = Error,
                errorContainerColor = Color(0xFFE8A3A3FF),
                focusedContainerColor = InputBg,
                unfocusedContainerColor = InputBg,
            ),
            singleLine = true
        )
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
