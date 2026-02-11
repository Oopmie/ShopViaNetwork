package com.example.uikit

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.uikit.ui.theme.Black
import com.example.uikit.ui.theme.Description
import com.example.uikit.ui.theme.InputBg
import com.example.uikit.ui.theme.InputStroke

//поисковая строка. при выделении появляется крестик для выхода из поиска
@Composable
fun Search(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(text = placeholder)
        },
        leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = Description
                )},
        shape = RoundedCornerShape(10.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = InputStroke,
            unfocusedBorderColor = InputStroke,
            focusedContainerColor = InputBg,
            unfocusedContainerColor = InputBg,
            focusedTextColor = Black,
            unfocusedTextColor = Black
        ),
        singleLine = true
    )
}

