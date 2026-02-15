package com.example.myapplication55.presentation.project

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.uikit.AppTextField
import com.example.uikit.MyDropdownField
import com.example.uikit.ui.theme.Accent
import com.example.uikit.ui.theme.AccentInactive
import com.example.uikit.ui.theme.Caption
import com.example.uikit.ui.theme.Description
import com.example.uikit.ui.theme.Title2
import com.example.uikit.ui.theme.White

@Composable
fun CreateProject(navController: NavController,) {
    Column(
        verticalArrangement = Arrangement.spacedBy(15.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp, 60.dp)
    ) {
        var name by remember { mutableStateOf("") }
        var nameError by remember { mutableStateOf(false) }
        var startDate by remember { mutableStateOf("") }
        var startDateError by remember { mutableStateOf(false) }
        var endDate by remember { mutableStateOf("") }
        var endDateError by remember { mutableStateOf(false) }
        var mail by remember { mutableStateOf("") }
        var mailError by remember { mutableStateOf(false) }
        Text(
            "Создать проект",
            style = Title2.titleMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxHeight(0.8f)
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(13.dp)
        ) {
            item {
                Text("Тип", color = Description, style = Caption.bodySmall)
                MyDropdownField(dropText = "Выберите  тип", categories = listOf("",""))
            }
            item {
                Text("Название проекта", color = Description, style = Caption.bodySmall)
                AppTextField(
                    value = name, onValueChange = {
                        name = it
                        if (it.isNotBlank()) nameError = false
                    },
                    isError = nameError, errorText = "Введите имя", placeholder = "Введите имя"
                )
            }
            item {
                Text("Дата начала", color = Description, style = Caption.bodySmall)
                AppTextField(
                    value = startDate, onValueChange = {
                        startDate = it
                        if (it.isNotBlank()) startDateError = false
                    },
                    isError = startDateError, errorText = "Введите дату начала", placeholder = "--.--.----"
                )
            }
            item {
                Text("Дата окончания", color = Description, style = Caption.bodySmall)
                AppTextField(
                    value = endDate, onValueChange = {
                        endDate = it
                        if (it.isNotBlank()) endDateError = false
                    },
                    isError = endDateError, errorText = "Введите дату окончания", placeholder = "--.--.----"
                )
            }
            item {
                Text("Кому", color = Description, style = Caption.bodySmall)
                MyDropdownField(dropText = "Выберите  кому", categories = listOf("Мужчинам","Женщинам"))
            }
            item {
                Text("Источник описания", color = Description, style = Caption.bodySmall)
                AppTextField(
                    value = mail, onValueChange = {
                        mail = it
                        if (it.isNotBlank()) mailError = false
                    },
                    isError = mailError, errorText = "Введите источник описания", placeholder = "example.com"
                )
            }
            item {
                Text("Категория", color = Description, style = Caption.bodySmall)
                MyDropdownField(dropText = "Выберите  категорию", categories = listOf("",""))
            }
            item {

            }
        }
        Button(modifier = Modifier.fillMaxWidth().padding(top = 30.dp).height(56.dp), onClick = {
            nameError=name.isBlank()
            startDateError=startDate.isBlank()
            endDateError=endDate.isBlank()
            mailError=mail.isBlank()
            if(!nameError&&!startDateError&&!endDateError&&!mailError){}
        },
            colors = ButtonColors(
                containerColor = Accent,
                contentColor = White,
                disabledContentColor = White,
                disabledContainerColor = AccentInactive
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text("Подтвердить")
        }
    }
}