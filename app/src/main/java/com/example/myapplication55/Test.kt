package com.example.myapplication55

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.uikit.BigButton
import com.example.uikit.CardScreen
import com.example.uikit.CategoryChip
import com.example.uikit.SpacerScreen
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.Alignment
import com.example.myapplication55.viewModel.ProductViewModel
import com.example.uikit.ui.theme.Accent
import com.example.uikit.ui.theme.Black
import org.koin.androidx.compose.koinViewModel


@Composable
fun RegistrationScreen() {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    val isFormValid = firstName.isNotBlank() && lastName.isNotBlank()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, 60.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = { Text("Имя") },
            modifier = Modifier.fillMaxWidth()
        )
        SpacerScreen(1.dp)
        OutlinedTextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text("Фамилия") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        BigButton(
            enabled = isFormValid,
            onClick = {},
            text = "Подтвердить"
        )
//        CardScreen("Рубашка Воскресенье для машинного вязания", "Мужская одежда","300 ₽")
    }
}
@Composable
fun MainProductScreen(
    viewModel: ProductViewModel = koinViewModel()
) {
    val products = viewModel.products
    val isLoading = viewModel.isLoading
    Box(modifier = Modifier.fillMaxSize()) {
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(products) { product ->
                    CardScreen(
                        product = product,
                        isAdded = viewModel.cartItemIds.contains(product.id),
                        onToggleClick = {
                            viewModel.toggleCart(product.id)
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainProductScreenPreview(){
    MainProductScreen()
}