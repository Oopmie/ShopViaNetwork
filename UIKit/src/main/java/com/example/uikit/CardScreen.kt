package com.example.uikit

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.network.model.ProductDescription
import com.example.network.model.SearchList
import com.example.uikit.ui.theme.Caption
import com.example.uikit.ui.theme.CaptionColor
import com.example.uikit.ui.theme.Headline
import com.example.uikit.ui.theme.Title2
import com.example.uikit.ui.theme.Title3

//карточки товаров на главной странице с кнопкой "Добавить", при нажатии она меняется на кнопку "Убрать"
//И отправляет товар в корзину.
@Composable
fun CardScreen(
    product: SearchList,
    description: ProductDescription?,
    isAdded: Boolean,
    onCardClick:()-> Unit,
    onToggleClick: (Boolean) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .clickable { showDialog = true }
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
                    Text(text = product.type, style = Caption.bodyMedium, color = CaptionColor)
                    Text(text = "${product.price} ₽", style = Title3.titleLarge)
                }
                SmallButton(
                    isAdded = isAdded,
                    onToggle = { onToggleClick(it) },
                    textDelete = "Убрать",
                    textAdd = "Добавить",
                    modifier = Modifier.width(130.dp).height(40.dp)
                )
            }
        }
    }

    if (showDialog) {
        Dialog(
            onDismissRequest = { showDialog = false },
            properties = DialogProperties(usePlatformDefaultWidth = false),
        ) {
            Box(
                contentAlignment = Alignment.BottomCenter,
                modifier = Modifier.fillMaxSize().clickable { showDialog = false }
            ) {
                Surface(
                    shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
                    color = Color.White,
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .clickable(enabled = false) {}
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight(0.8f)
                            .padding(16.dp)
                            .navigationBarsPadding(),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = product.title, style = Title2.headlineMedium)
                        Text(text = "Описание", style = Headline.headlineMedium, color = Color(0xFF939396))
                        Text(
                            text = description?.description ?:"Загрузка..",
                            style = Headline.headlineMedium,
                            modifier = Modifier.weight(1f, fill = false)
                        )

                        Column {
                            Text(text = "Примерный расход:", style = Caption.bodyMedium, color = Color(0xFF939396))
                            Text(text = "${description?.approximateCost} г", style = Headline.headlineMedium, color = Color.Black)
                        }
                        SmallButton(
                            isAdded = isAdded,
                            onToggle = { onToggleClick(it) },
                            textDelete = "Убрать",
                            textAdd = "Добавить за ${product.price} ₽",
                            modifier = Modifier.fillMaxWidth().height(56.dp)
                        )
                    }
                }
            }
        }
    }
}
