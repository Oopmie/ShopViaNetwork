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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.uikit.ui.theme.Black
import com.example.uikit.ui.theme.Description
import com.example.uikit.ui.theme.InputBg
import com.example.uikit.ui.theme.InputStroke

//поисковая строка.
@Composable
fun Search() {
    var searchIn by remember { mutableStateOf("") }
    OutlinedTextField(
        value = searchIn,
        onValueChange = {searchIn=it},
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Искать описания") },
        leadingIcon = { Icon( imageVector = Icons.Default.Search,
            contentDescription = "Search",
            tint = Description)},
        shape = RoundedCornerShape(10.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = InputStroke,
            unfocusedBorderColor = InputStroke,
            focusedContainerColor = InputBg,
            unfocusedContainerColor = InputBg,
            focusedTextColor = Black,
            unfocusedTextColor = Black ),
        singleLine = true) }