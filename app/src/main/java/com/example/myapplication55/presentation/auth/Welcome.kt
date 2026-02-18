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
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication55.R
import com.example.myapplication55.viewModel.AuthViewModel
import com.example.uikit.AppTextField
import com.example.uikit.Login
import com.example.uikit.PassTextField
import com.example.uikit.ui.theme.Accent
import com.example.uikit.ui.theme.AccentInactive
import com.example.uikit.ui.theme.Black
import com.example.uikit.ui.theme.Caption
import com.example.uikit.ui.theme.Description
import com.example.uikit.ui.theme.Text
import com.example.uikit.ui.theme.Title1
import com.example.uikit.ui.theme.White

@Composable
fun Welcome(
    navController: NavController, viewModel: AuthViewModel,
    onNavigateToHome: () -> Unit, onNavigateToRegister: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(20.dp, 60.dp)
    ) { Column(verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth().height(100.dp)
        ) { Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) { Image(modifier = Modifier.size(40.dp),
                    bitmap = ImageBitmap.imageResource(R.drawable.hi),
                    contentDescription = "hand")
                Text("Добро пожаловать!", style = Title1.titleLarge, color = Black) }
            Text("Войдите, чтобы пользоваться функциями приложения", style = Text.bodySmall)}
        Column(modifier = Modifier.padding(vertical = 60.dp).fillMaxHeight(0.57f),
            verticalArrangement = Arrangement.SpaceBetween
        ) { var mail by remember { mutableStateOf("") }
            var mailError by remember { mutableStateOf(false) }
            var pass by remember { mutableStateOf("") }
            var passError by remember { mutableStateOf(false) }
            Text("Вход по E-mail", style = Caption.bodySmall, color = Description)
            AppTextField(placeholder = "example@mail.com", errorText = "Введите почту",
                value = mail, onValueChange = {
                    mail = it
                    if (it.isNotBlank()) mailError = false
                }, isError = mailError )
            Text("Пароль", style = Caption.bodySmall, color = Description)
            PassTextField(value = pass, onValueChange = {
                pass = it
                if (it.isNotBlank()) passError = false
            }, isError = passError, errorText = "Введите пароль")
            Button(modifier = Modifier.fillMaxWidth().height(56.dp), onClick = {
                    mailError = mail.isBlank()
                    passError = pass.isBlank()
                    if (!mailError && !passError) {
                        viewModel.signIn(email = mail, pass = pass, onHome = {
                                navController.navigate("homepage") {
                                    popUpTo("welcome") { inclusive = true }
                                } }, onRegister = {
                                navController.navigate("create_account") }) }
                }, colors = ButtonColors(containerColor = Accent, contentColor = White,
                    disabledContentColor = White, disabledContainerColor = AccentInactive ),
                shape = RoundedCornerShape(10.dp)
            ) { Text("Далее") }
            TextButton(onClick = onNavigateToRegister) {
                Text("Зарегистрироваться", color = Accent, fontSize = 15.sp,
                    textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth()) } }
        Login(imageRes1 = R.drawable.vk, imageRes2 = R.drawable.yandex)
    }
}