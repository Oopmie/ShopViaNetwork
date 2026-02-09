package com.example.myapplication55.test

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.uikit.AppTextField
import com.example.uikit.ui.theme.Black

@Composable
fun InputTestScreen(){
    var firstName by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }
    var firstNameError by remember { mutableStateOf(false) }
    var surnameError by remember { mutableStateOf(false) }
    Column(Modifier.padding(16.dp,40.dp).fillMaxHeight(0.25f),
        verticalArrangement = Arrangement.SpaceBetween) {
        AppTextField(
            placeholder = "Введите имя", errorText = "Введите ваше имя",
            modifier = Modifier.fillMaxWidth().wrapContentHeight(), value = firstName, onValueChange = {firstName=it
            if(it.isNotBlank()) firstNameError=false}, isError = firstNameError)
        AppTextField(
            placeholder = "Введите фамилию", errorText = "Введите вашу фамилию",
            modifier = Modifier.fillMaxWidth().wrapContentHeight(), value = surname, onValueChange = {surname=it
            if(it.isNotBlank())surnameError=false}, isError = surnameError)
        Button(modifier = Modifier.fillMaxWidth().height(50.dp), onClick = {
            firstNameError=firstName.isBlank()
            surnameError=surname.isBlank()
            if(!firstNameError&&!surnameError){}
        },
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