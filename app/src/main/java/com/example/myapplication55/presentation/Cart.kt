package com.example.myapplication55.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication55.viewModel.ProductViewModel
import com.example.network.model.CartResponse
import com.example.uikit.BigButton
import com.example.uikit.CardCart
import com.example.uikit.ui.theme.Accent
import com.example.uikit.ui.theme.Description
import com.example.uikit.ui.theme.InputBg
import com.example.uikit.ui.theme.Title1
import com.example.uikit.ui.theme.Title2
import com.example.uikit.ui.theme.White
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun CardInCartScreen(navController: NavController, viewModel: ProductViewModel = koinViewModel()) {
    val product = viewModel.products.firstOrNull()
    val description = viewModel.description
    val totalPrice = viewModel.totalCartPrice
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) { data ->
                Snackbar(
                    containerColor = Accent,
                    contentColor = White,
                    shape = RoundedCornerShape(12.dp),
                    snackbarData = data
                )
            }
        },
        containerColor = White
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.SpaceBetween ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.15f),
                verticalArrangement = Arrangement.SpaceBetween ) {
                IconButton(
                    modifier = Modifier
                        .size(35.dp)
                        .background(InputBg, RoundedCornerShape(8.dp)),
                    onClick = { navController.popBackStack() }, ) {
                    Icon(
                        Icons.Filled.ArrowBackIosNew,
                        "back",
                        tint = Description,
                        modifier = Modifier.size(18.dp) ) }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically ) {
                    Text("Корзина", style = Title1.titleLarge)
                        IconButton(onClick = { viewModel.clearFullCart() }) {
                        Icon(Icons.Filled.DeleteOutline, "delete", tint = Description) }
                }
            }
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                val cartEntries = viewModel.cartMap.value.toList()

                items(
                    cartEntries,
                    key = { it.first })
                { (productId, count) ->
                    val product = viewModel.products.find { it.id == productId }
                    if (product != null)
                        CardCart(
                            product = product,
                            cartEntry = CartResponse(
                                id = productId,
                                productId = productId,
                                count = count,
                                userId = "",
                                collectionId = "",
                                collectionName = "",
                                created = "",
                                updated = ""
                            ),
                            onDeleteClick = {
                                viewModel.deleteFromCart(productId)
                            },
                            onCountUpdate = { newCount ->
                                viewModel.updateCartCount(productId, newCount)
                            }
                        )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Сумма", style = Title2.titleMedium)
                    Text("$totalPrice ₽", style = Title2.titleMedium)
                }
                Spacer(modifier = Modifier.height(20.dp))
                BigButton(
                    enabled = totalPrice > 0,
                    text = "Перейти к оформлению заказа",
                    onClick = {
                        scope.launch {
                            val job = launch {
                                snackbarHostState.showSnackbar(
                                    message = "Заказ успешно оформлен!",
                                    duration = SnackbarDuration.Indefinite ) }
                            delay(2000)
                            viewModel.clearFullCart()
                            job.cancel()
                            navController.navigate("homepage") {
                                popUpTo("homepage") { inclusive = true } }
                        }
                    }
                )
            }
        }
    }
}