package com.example.myapplication55.viewModel

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.derivedStateOf
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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ProductViewModel(
    val api: UserAPI,
    private val sessionManager: SessionManager
) : ViewModel() {
    var products by mutableStateOf<List<SearchList>>(emptyList())
    var description by mutableStateOf<ProductDescription?>(null)
    var cartMap = mutableStateOf<Map<String, Int>>(emptyMap())
        private set
    val addedProductIds = derivedStateOf { cartMap.value.keys }

    val totalCartPrice: Int
        get() {
            val currentCart = cartMap.value
            return currentCart.entries.sumOf { (productId, count) ->
                val product = products.find { it.id == productId }
                val price = product?.price ?: 0
                price * count
            }
        }

    fun updateCartCount(productId: String, newCount: Int) {
        if (newCount < 1) {
            deleteFromCart(productId)
        } else {
            cartMap.value = cartMap.value.toMutableMap().apply {
                this[productId] = newCount
            }
        }
    }

    fun toggleProduct(productId: String) {
        if (cartMap.value.containsKey(productId)) {
            deleteFromCart(productId)
        } else {
            cartMap.value = cartMap.value + (productId to 1)
        }
    }

    fun deleteFromCart(productId: String) {
        Log.e("CART_DEBUG", "Удаляем товар: $productId")
        cartMap.value = cartMap.value - productId
        Log.e("CART_DEBUG", "Осталось товаров: ${cartMap.value.size}")
    }

    fun checkout(onComplete: () -> Unit) {
        viewModelScope.launch {
            delay(500)
            cartMap.value = emptyMap()
            onComplete()
        }
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

    fun clearFullCart() {
        val newEmptyMap = emptyMap<String, Int>()
        cartMap.value = newEmptyMap
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

