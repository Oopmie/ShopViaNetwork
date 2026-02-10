package com.example.myapplication55.test

import androidx.compose.runtime.Composable
import com.example.myapplication55.viewModel.ProductViewModel
import com.example.uikit.CardCart
import org.koin.androidx.compose.koinViewModel

@Composable
fun CardInCartScreen(viewModel: ProductViewModel= koinViewModel()){
    val product = viewModel.products.firstOrNull()
    val description = viewModel.description.firstOrNull()
    if (product != null && description != null) {
        CardCart(
            product = product,
            description = description
        )
    }
}
//@Preview(showBackground = true)
//@Composable
//fun CartCardPreview(){
//    CardInCart()
//}