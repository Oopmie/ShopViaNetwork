package com.example.myapplication55.presentation.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.uikit.AppTextField
import com.example.uikit.GenderDropdownField
import com.example.uikit.ui.theme.Accent
import com.example.uikit.ui.theme.AccentInactive
import com.example.uikit.ui.theme.Caption
import com.example.uikit.ui.theme.CaptionColor
import com.example.uikit.ui.theme.Title1
import com.example.uikit.ui.theme.White

@Composable
fun CreateAccount() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp, 60.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text("Создание Профиля", style = Title1.titleLarge)
        Column(verticalArrangement = Arrangement.spacedBy(15.dp)) {
            Text(
                "Без профиля вы не сможете создавать проекты.",
                style = Caption.bodySmall,
                color = CaptionColor
            )
            Text(
                "В профиле будут храниться результаты проектов и ваши описания.",
                style = Caption.bodySmall,
                color = CaptionColor
            )
        }
        Column(
            verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                .fillMaxHeight(0.85f)
        ) {
            var name by remember { mutableStateOf("") }
            var nameError by remember { mutableStateOf(false) }
            var secondName by remember { mutableStateOf("") }
            var secondNameError by remember { mutableStateOf(false) }
            var surname by remember { mutableStateOf("") }
            var surnameError by remember { mutableStateOf(false) }
            var birthday by remember { mutableStateOf("") }
            var birthdayError by remember { mutableStateOf(false) }
            var mail by remember { mutableStateOf("") }
            var mailError by remember { mutableStateOf(false) }

            AppTextField(value = name, onValueChange = {
                name = it
                if (it.isNotBlank()) nameError = false
            }, isError = nameError, placeholder = "Имя", errorText = "Введите имя")
            AppTextField(value = secondName, onValueChange = {
                secondName = it
                if (it.isNotBlank()) secondNameError = false
            }, isError = secondNameError, placeholder = "Отчество", errorText = "Введите отчество")
            AppTextField(value = surname, onValueChange = {
                surname = it
                if (it.isNotBlank()) surnameError = false
            }, isError = surnameError, placeholder = "Фамилия", errorText = "Введите фамилию")
            AppTextField(value = birthday, onValueChange = {
                birthday = it
                if (it.isNotBlank()) birthdayError = false
            }, isError = birthdayError, placeholder = "Дата рождения", errorText = "Введите дату рождения")
            GenderDropdownField()
            AppTextField(value = mail, onValueChange = {
                mail = it
                if (it.isNotBlank()) mailError = false
            }, isError = mailError, placeholder = "Почта", errorText = "Введите почту")

            Button(modifier = Modifier.fillMaxWidth().padding(top = 30.dp).height(56.dp), onClick = {
                nameError=name.isBlank()
                secondNameError=secondName.isBlank()
                surnameError=surname.isBlank()
                birthdayError=birthday.isBlank()
                mailError=mail.isBlank()
                if(!nameError&&!secondNameError&&!surnameError&&!birthdayError&&!mailError){}
            },
                colors = ButtonColors(
                    containerColor = Accent,
                    contentColor = White,
                    disabledContentColor = White,
                    disabledContainerColor = AccentInactive
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text("Далее")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Ss() {
    CreateAccount()
}