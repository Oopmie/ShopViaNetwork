package com.example.myapplication55.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.myapplication55.R
import com.example.myapplication55.viewModel.ProductViewModel
import com.example.uikit.CardScreen
import com.example.uikit.CategoryLazy
import com.example.uikit.Search
import com.example.uikit.ui.theme.CaptionColor
import com.example.uikit.ui.theme.Title2
import com.example.uikit.ui.theme.Title3
import com.example.uikit.ui.theme.White
import org.koin.androidx.compose.koinViewModel

@Composable
fun Catalog(viewModel: ProductViewModel = koinViewModel()) {
    var selectedCategory by remember { mutableStateOf("Все") }
    var searchIn by remember { mutableStateOf("") }

    LaunchedEffect(selectedCategory) {
        viewModel.loadProducts(selectedCategory)
    }
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp, 60.dp)
    ) {
        val (search, textSales, card, catalogDesc, lazyCat, productList) = createRefs()
        Box(modifier = Modifier.constrainAs(search) {
            top.linkTo(parent.top, 0.dp)
        }) {
            Search(
                value = searchIn,
                onValueChange = { searchIn = it },
                placeholder = "        Искать описания"
            )
        }
        Text(
            "Акции и новости",
            style = Title3.titleLarge,
            color = CaptionColor,
            modifier = Modifier.constrainAs(textSales) {
                top.linkTo(search.bottom, 30.dp)
            })
        LazyRow(
            modifier = Modifier
                .height(170.dp)
                .constrainAs(card) {
                    top.linkTo(textSales.bottom, 15.dp)
                }) {
            item {
                Card(
                    modifier = Modifier
                        .padding(end = 20.dp)
                        .width(280.dp),
                    colors = CardDefaults.cardColors(Color(0xFF97D9F0)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            modifier = Modifier.fillMaxHeight(),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Шорты \nВторник", color = White, style = Title2.titleLarge)
                            Text("4000 ₽", color = White, style = Title2.titleLarge)
                        }
                        Image(
                            modifier = Modifier.size(150.dp),
                            bitmap = ImageBitmap.imageResource(R.drawable.covid),
                            contentDescription = "covid",
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
            item {
                Card(
                    modifier = Modifier.width(280.dp),
                    colors = CardDefaults.cardColors(Color(0xFF76B3FF)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            modifier = Modifier.fillMaxHeight(),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Рубашка \nВоскресенье", color = White, style = Title2.titleLarge)
                            Text("8000 ₽", color = White, style = Title2.titleLarge)
                        }
                        Image(
                            modifier = Modifier.size(150.dp),
                            bitmap = ImageBitmap.imageResource(R.drawable.manoncard),
                            contentDescription = "covid",
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        }
        Text("Каталог описаний", modifier = Modifier.constrainAs(catalogDesc) {
            top.linkTo(card.bottom, 30.dp)
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
                    // Описание из ViewModel подгрузится внутри карточки по нажатию
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

@Preview(showBackground = true)
@Composable
fun CatalogPreview() {
    Catalog()
}