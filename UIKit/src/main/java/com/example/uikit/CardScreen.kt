package com.example.uikit

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.network.model.SearchList
import com.example.uikit.ui.theme.Caption
import com.example.uikit.ui.theme.Headline
import com.example.uikit.ui.theme.Title3

//карточки товаров на главной странице с кнопкой "Добавить", при нажатии она меняется на кнопку "Убрать"
//И отправляет товар в корзину.
@Composable
fun CardScreen(
    product: SearchList,
    isAdded: Boolean,
    onToggleClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(160.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, color = Color(0xFFF4F4F4)),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = product.title, style = Headline.headlineMedium)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Column {
                    Text(text = product.type, style = Caption.bodyMedium)
                    Text(text = "${product.price} ₽", style = Title3.titleLarge)
                }
                SmallButton(
                    isAdded = isAdded,
                    onToggle = { onToggleClick() },
                    textDelete = "Убрать",
                    textAdd = "Добавить"
                )
            }
        }
    }
}
