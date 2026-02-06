package com.example.uikit

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.uikit.ui.theme.Black
import com.example.uikit.ui.theme.Caption
import com.example.uikit.ui.theme.Headline
import com.example.uikit.ui.theme.Title3
import com.example.uikit.ui.theme.White

//карточки товаров на главной странице с кнопкой "Добавить", при нажатии она меняется на кнопку "Убрать"
@Composable
fun CardScreen(text: String, category: String, price: String){
    Card(modifier = Modifier.height(160.dp).fillMaxWidth(),
        colors = CardColors(containerColor = White, contentColor = Black, disabledContainerColor = White, disabledContentColor = Black),
        border = BorderStroke(1.dp, color = Color(0xFFF4F4F4)),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Column(modifier = Modifier.fillMaxHeight().padding(16.dp), verticalArrangement = Arrangement.SpaceBetween) {
            Text(text = text, style = Headline.headlineMedium)
            Spacer(modifier = Modifier.fillMaxHeight(0.5f))
            Row(modifier = Modifier.height(70.dp).fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Column(Modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceBetween) {
                    Text(text = category, style = Caption.bodyMedium)
                    Text(text = price, style = Title3.titleLarge)
                }
                SmallButton({}, "Убрать", "Добавить")
            }
        }
    }
}