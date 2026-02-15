package com.example.myapplication55.presentation.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication55.R
import com.example.myapplication55.viewModel.AuthViewModel
import com.example.myapplication55.viewModel.ProductViewModel
import com.example.uikit.PassTextField
import com.example.uikit.ui.theme.Accent
import com.example.uikit.ui.theme.AccentInactive
import com.example.uikit.ui.theme.Black
import com.example.uikit.ui.theme.Caption
import com.example.uikit.ui.theme.Description
import com.example.uikit.ui.theme.Text
import com.example.uikit.ui.theme.Title1
import com.example.uikit.ui.theme.White
import org.koin.androidx.compose.koinViewModel

@Composable
fun CreatePass(navController: NavController, viewModel: AuthViewModel = koinViewModel(), onSuccess: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp, 60.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Image(
                    modifier = Modifier.size(40.dp),
                    bitmap = ImageBitmap.imageResource(R.drawable.hi),
                    contentDescription = "hand"
                )
                Text("Добро пожаловать!", style = Title1.titleLarge, color = Black)
            }
            Text("Войдите, чтобы пользоваться функциями приложения", style = Text.bodySmall)
        }
        Column(
            modifier = Modifier
                .padding(vertical = 60.dp)
                .fillMaxHeight(0.57f),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            var pass1 by remember { mutableStateOf("") }
            var pass1Error by remember { mutableStateOf(false) }
            var pass2 by remember { mutableStateOf("") }
            var pass2Error by remember { mutableStateOf(false) }
            Text("Новый пароль", style = Caption.bodySmall, color = Description)
            PassTextField(
                value = pass1, onValueChange = {
                    pass1 = it
                    if (it.isNotBlank()) pass1Error = false
                }, isError = pass1Error, errorText = "Введите пароль"
            )
            Text("Повторите пароль", style = Caption.bodySmall, color = Description)
            PassTextField(value = pass2, onValueChange = {
                pass2 = it
                if (it.isNotBlank()) pass2Error = false
            }, isError = pass2Error, errorText = "Пароли не совпадают")
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp), onClick = {
                    pass1Error = pass1.isBlank()
                    pass2Error = pass2.isBlank() || pass1 != pass2
                    if (pass1 == pass2) {
                        viewModel.registerAndSaveAll(pass1) {
                            onSuccess()
                            navController.navigate("homepage")
                        }
                    }
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