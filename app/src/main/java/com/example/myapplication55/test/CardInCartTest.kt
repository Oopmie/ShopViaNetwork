package com.example.myapplication55.test

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication55.viewModel.ProductViewModel
import com.example.uikit.BigButton
import com.example.uikit.ui.theme.Description
import com.example.uikit.ui.theme.InputBg
import com.example.uikit.ui.theme.Title1
import com.example.uikit.ui.theme.Title2
import org.koin.androidx.compose.koinViewModel

@Composable
fun CardInCartScreen(viewModel: ProductViewModel = koinViewModel()) {
//    val product = viewModel.products.firstOrNull()
//    val description = viewModel.description
//    if (product != null && description != null) {
//        CardCart(
//            product = product,
//            description = description
//        )
//    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp, 60.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        val totalPrice = viewModel.totalCartPrice
        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.15f), verticalArrangement = Arrangement.SpaceBetween) {
            IconButton(
                modifier = Modifier
                    .size(35.dp)
                    .background(InputBg),
                shape = RoundedCornerShape(8.dp),
                onClick = {},
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBackIosNew,
                    contentDescription = "back",
                    tint = Description
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Корзина", style = Title1.titleLarge)
                IconButton(
                    modifier = Modifier.size(35.dp), shape = RoundedCornerShape(8.dp),
                    onClick = {}) {
                    Icon(
                        imageVector = Icons.Filled.DeleteOutline,
                        contentDescription = "back",
                        tint = Description
                    )
                }
            }
        }
        LazyColumn(modifier = Modifier.fillMaxHeight(0.6f).fillMaxWidth().padding(top = 20.dp), verticalArrangement = Arrangement.spacedBy(30.dp)) { }
        Row(modifier = Modifier.fillMaxWidth().padding(bottom = 165.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Сумма", style = Title2.titleMedium)
            Text("${totalPrice} ₽", style = Title2.titleMedium)
        }
        BigButton(enabled = true, onClick = {}, text = "Перейти к оформлению заказа")
    }
}

//@Preview(showBackground = true)
//@Composable
//fun CartCardPreview() {
//    CardInCartScreen()
//}