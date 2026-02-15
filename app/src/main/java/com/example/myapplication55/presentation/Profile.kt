package com.example.myapplication55.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication55.R
import com.example.myapplication55.viewModel.AuthViewModel
import com.example.uikit.ToggleButtonSc
import com.example.uikit.ui.theme.Black
import com.example.uikit.ui.theme.CaptionColor
import com.example.uikit.ui.theme.Error
import com.example.uikit.ui.theme.Headline
import com.example.uikit.ui.theme.Text
import com.example.uikit.ui.theme.Title1
import com.example.uikit.ui.theme.Title3
import org.koin.androidx.compose.koinViewModel

@Composable
fun Profile(navController: NavController, viewModel: AuthViewModel = koinViewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp, 60.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(0.5f)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(50.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(15.dp)) {
                Text(text = viewModel.userName, style = Title1.titleLarge)
                Text(
                    text = viewModel.userEmail,
                    style = Headline.headlineSmall,
                    color = CaptionColor
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Image(
                        bitmap = ImageBitmap.imageResource(R.drawable.board),
                        contentDescription = "board", modifier = Modifier.size(40.dp)
                    )
                    TextButton({}) { Text("Мои заказы", color = Black, style = Title3.titleLarge) }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 26.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(33.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            bitmap = ImageBitmap.imageResource(R.drawable.sett),
                            contentDescription = "setting", modifier = Modifier.size(40.dp)
                        )
                        Text("Уведомления", color = Black, style = Title3.titleLarge)
                    }
                    ToggleButtonSc()
                }
            }
        }
        Column(
            modifier = Modifier
                .weight(0.5f)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextButton({}) {
                Text("Политика конфиденциальности", style = Text.bodyMedium, color = CaptionColor)
            }
            TextButton({}) {
                Text("Пользовательское соглашение", style = Text.bodyMedium, color = CaptionColor)
            }
            TextButton({
                viewModel.logout {
                    navController.navigate("welcome") {
                        popUpTo(0) { inclusive = true }
                    }
                }
            }) {
                Text("Выход", style = Text.bodyMedium, color = Error)

            }
        }
    }
}