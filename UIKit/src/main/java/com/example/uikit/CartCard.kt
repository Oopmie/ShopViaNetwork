package com.example.uikit

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
import com.example.network.model.CartResponse
import com.example.network.model.ProductDescription
import com.example.network.model.SearchList
import com.example.uikit.ui.theme.Black
import com.example.uikit.ui.theme.Caption
import com.example.uikit.ui.theme.Headline
import com.example.uikit.ui.theme.Title2
import com.example.uikit.ui.theme.Title3
import com.example.uikit.ui.theme.White

@Composable
fun CardCart(
    product: SearchList,
    cartCount: CartResponse,
    onDeleteClick: () -> Unit,
    onCountUpdate: (Int) -> Unit) {
    Card(modifier = Modifier.fillMaxWidth().height(160.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, color = Color(0xFFF4F4F4)),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)) {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween) {
            Row(horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.wrapContentHeight().fillMaxWidth()) {
                Text(text = product.title,
                    style = Headline.headlineMedium,
                    modifier = Modifier.wrapContentHeight().fillMaxWidth(0.85f))
                Button(onClick = onDeleteClick,
                    modifier = Modifier.size(20.dp),
                    colors = ButtonColors(
                        containerColor = White, contentColor = Black,
                        disabledContainerColor = White, disabledContentColor = Black),
                    contentPadding = PaddingValues(0.dp)) {
                    Icon(imageVector = Icons.Default.Close,
                        contentDescription = "delete",
                        tint = Black) } }
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom) {
                Text(text = "${product.price} ₽", style = Title3.titleLarge)
                CounterButton(initialCount = cartCount.count, onCountChange = onCountUpdate) }
        }
    }
}