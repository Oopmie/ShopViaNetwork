package com.example.myapplication55.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.myapplication55.viewModel.ProductViewModel
import com.example.uikit.CardScreen
import com.example.uikit.CategoryLazy
import com.example.uikit.Search
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import com.example.uikit.ui.theme.Black
import com.example.uikit.ui.theme.CaptionColor
import com.example.uikit.ui.theme.Title3
import com.example.uikit.ui.theme.White
import org.koin.androidx.compose.koinViewModel

@Composable
fun Catalog(viewModel: ProductViewModel = koinViewModel(), onNavigateToCart: () -> Unit = {}) {
    var selectedCategory by remember { mutableStateOf("Все") }
    var searchIn by remember { mutableStateOf("") }
    val totalPrice = viewModel.totalCartPrice

    LaunchedEffect(selectedCategory) {
        viewModel.loadProducts(selectedCategory)
    }
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp, 60.dp)
    ) {
        val (search, catalogDesc, lazyCat, productList, btnGoToCart) = createRefs()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(search) {
                    top.linkTo(parent.top, 0.dp)
                }, verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Box(modifier = Modifier.weight(1f)) {
                Search(
                    value = searchIn,
                    onValueChange = { searchIn = it },
                    placeholder = "Искать описания"
                )
            }
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Search",
                tint = Black,
                modifier = Modifier.size(50.dp)
            )
        }
        Text(
            "Каталог описаний",
            style = Title3.titleLarge,
            color = CaptionColor,
            modifier = Modifier.constrainAs(catalogDesc) {
                top.linkTo(search.bottom, 30.dp)
            })
        Box(modifier = Modifier.constrainAs(lazyCat) {
            top.linkTo(catalogDesc.bottom, 15.dp)
        }) {
            CategoryLazy(
                selected = selectedCategory,
                onCategoryClick = { selectedCategory = it }
            )
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(PaddingValues(vertical = 15.dp))
                .constrainAs(productList) {
                    top.linkTo(lazyCat.bottom, 15.dp)
                }) {
            items(viewModel.products) { itemProduct ->
                CardScreen(
                    product = itemProduct,
                    description = viewModel.description,
                    isAdded = viewModel.addedProductIds.value.contains(itemProduct.id),
                    onCardClick = {
                        viewModel.loadDescription(itemProduct.id)
                    },
                    onToggleClick = {
                        viewModel.toggleProduct(itemProduct.id)
                    }
                )
            }
        }
        Button(
            onClick = { onNavigateToCart() },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .constrainAs(btnGoToCart) {
                    bottom.linkTo(parent.bottom, 20.dp)
                },
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007AFF))
        ) {
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = "Cart",
                tint = White
            )
            Text(
                "      В корзину                                        ${totalPrice} ₽",
                color = Color.White
            )
        }
    }
}

//@Composable
//fun ComposableScreen() {
//    Scaffold(
//        bottomBar = {
//            PacktBottomNavigationBar()
//        }
//    ) { innerPadding ->
//        Box(modifier = Modifier.padding(innerPadding)) {
//            Catalog()
//        }
//    }
//}