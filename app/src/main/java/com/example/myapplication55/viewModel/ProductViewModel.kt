package com.example.myapplication55.viewModel

import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication55.data.SessionManager
import com.example.network.UserAPI
import com.example.network.model.CartRequest
import com.example.network.model.OrderRequest
import com.example.network.model.ProductDescription
import com.example.network.model.SearchList
import kotlinx.coroutines.launch

class ProductViewModel(
    val api: UserAPI,
    private val sessionManager: SessionManager
) : ViewModel() {
    var products by mutableStateOf<List<SearchList>>(emptyList())
    var description by mutableStateOf<ProductDescription?>(null)
    var addedProductIds = mutableStateOf(setOf<String>())
        private set
    val totalCartPrice: Int
        get() = products
            .filter { it.id in addedProductIds.value }
            .sumOf { it.price }

    fun checkout(onComplete: () -> Unit) {
        viewModelScope.launch {
            addedProductIds.value = emptySet()
            onComplete()
        }
    }

    fun toggleProduct(productId: String) {
        if (addedProductIds.value.contains(productId)) {
            addedProductIds.value = addedProductIds.value - productId
        } else {
            addToCart(productId)
        }
    }

    fun addToCart(productId: String) {
        viewModelScope.launch {
            val userId = sessionManager.getUserId() ?: "guest"
            val request = CartRequest(userId = userId, productId = productId, count = 1)
            val response = api.createCart(request)
            if (response.isSuccessful) {
                addedProductIds.value = addedProductIds.value + productId
            }
        }
    }
    fun deleteFromCart(productId: String) {
        addedProductIds.value = addedProductIds.value - productId
    }

    fun loadProducts(category: String) {
        viewModelScope.launch {
            val token = "Bearer ${sessionManager.getToken()}"
            val filter = if (category == "Все") null else "type='$category'"
            val response = api.productList(token, filter)
            if (response.isSuccessful) {
                products = response.body()?.items ?: emptyList()
            }
        }
    }

    init {
        products = listOf(
            SearchList(
                id = "1",
                title = "Мужская рубашка",
                price = 300,
                type = "Мужчинам",
                typeCloses = "хм"
            ),
            SearchList(
                id = "2",
                title = "Женское платье",
                price = 500,
                type = "Женщинам",
                typeCloses = "хм"
            )
        )
    }

    fun loadDescription(productId: String) {
        description = ProductDescription(
            id = productId,
            collectionId = "col1",
            collectionName = "Одежда",
            created = "", updated = "",
            title = "Детальное описание товара",
            description = "Это тестовое описание для товара с ID $productId. " +
                    "Оно должно отображаться в твоем модальном окне при нажатии на карточку.",
            price = 300,
            typeCloses = "хм",
            type = "Мужчинам",
            approximateCost = "80-100"
        )
    }
}

