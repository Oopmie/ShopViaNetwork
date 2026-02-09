package com.example.myapplication55.test

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.uikit.AppTextField
import com.example.uikit.ui.theme.Black

@Composable
fun InputTestScreen(){
    Column(Modifier.padding(16.dp,40.dp).fillMaxHeight(0.25f),
        verticalArrangement = Arrangement.SpaceBetween) {
        AppTextField(
            placeholder = "Введите имя", errorText = "Введите ваше имя",
            modifier = Modifier.fillMaxWidth().height(50.dp), value = "", onValueChange = {it})
        AppTextField(
            placeholder = "Введите фамилию", errorText = "Введите вашу фамилию",
            modifier = Modifier.fillMaxWidth().height(50.dp), value = "", onValueChange = {it})
        Button(modifier = Modifier.fillMaxWidth().height(50.dp), onClick = {},
            colors = ButtonColors(containerColor = Color.LightGray,
            contentColor = Black, disabledContentColor = Black, disabledContainerColor = Color.LightGray),
            border = BorderStroke(1.dp, color = Color.Gray), shape = RoundedCornerShape(10.dp)
        ) {
            Text("Подтвердить")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InputTestScreenPreview(){
    InputTestScreen()
}