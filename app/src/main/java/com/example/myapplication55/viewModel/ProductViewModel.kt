package com.example.myapplication55.viewModel

import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication55.data.AppRepository
import com.example.myapplication55.data.SessionManager
import com.example.network.UserAPI
import com.example.network.model.OrderRequest
import com.example.network.model.ProductDescription
import com.example.network.model.SearchList
import kotlinx.coroutines.launch

class ProductViewModel(
    val api: UserAPI, private val sessionManager: SessionManager
) : ViewModel() {
    var products by mutableStateOf<List<SearchList>>(emptyList())
    var description by mutableStateOf<ProductDescription?>(null)
    var addedProductIds = mutableStateOf(setOf<String>())
        private set

    fun toggleProduct(productId: String) {
        val current = addedProductIds.value.toMutableSet()
        if (current.contains(productId)) current.remove(productId)
        else current.add(productId)
        addedProductIds.value = current
    }

    fun loadProducts(category: String) {
        viewModelScope.launch {
            val filter = if (category == "Все") null else "type='$category'"
            val response = api.productList(filter)
            if (response.isSuccessful) {
                products = response.body()?.items ?: emptyList()
            }
        }
    }

    fun loadDescription(productId: String) {
        viewModelScope.launch {
            description = null
            val response = api.productDescription(productId)
            if (response.isSuccessful) {
                description = response.body()
            }
        }
    }


//    init {
//        products = listOf(
//            SearchList(
//                id = "1", title = "Рубашка Воскресенье для машинного вязания", price = 300, type = "Мужская одежда",
//                typeCloses = "хм"
//            )
//        )
//        loadProducts()
//        description= listOf(
//            ProductDescription(
//                id = "1", collectionId = "1", collectionName = "одежда", created = "ss", updated = "sd", title = "d",
//                description = "Мой выбор для этих шапок – кардные составы, которые раскрываются деликатным пушком. Кашемиры, мериносы, смесовки с ними отлично подойдут на шапку.\n" +
//                        "Кардные составы берите в большое количество сложений, вязать будем резинку 1х1, плотненько.\n" +
//                        "Пряжу 1400-1500м в 100г в 4 сложения, пряжу 700м в 2 сложения. Ориентир для конечной толщины – 300-350м в 100г.\n" +
//                        "Артикулы, из которых мы вязали эту модель: Zermatt Zegna Baruffa, Cashfive, Baby Cashmere Loro Piana, Soft Donegal и другие.\n" +
//                        "Примерный расход на шапку с подгибом 70-90г.", price = 300, typeCloses = "хм", type = "Мужская одежда", approximateCost = "80-90"
//            )
//        )
//    }

//
//    fun loadProducts(query: String = "") {
//        viewModelScope.launch {
//            isLoading = true
//            val result = repository.getProducts(query)
//
//            result.onSuccess { list ->
//                products = list
//            }.onFailure {
//            }
//            isLoading = false
//        }
//    }
//
//    fun toggleCart(productId: String) {
//        val currentUserId = sessionManager.getUserId()
//        if (currentUserId == null) {
//            return
//        }
//        viewModelScope.launch {
//            val result = repository.addToCart(productId, currentUserId)
//            result.onSuccess {
//                cartItemIds.add(productId)
//            }.onFailure {
//            }
//        }
//    }
//
//    fun placeOrder(productId: String) {
//        val userId = sessionManager.getUserId()
//        if (userId == null) {
//            return
//        }
//        viewModelScope.launch {
//            val request = OrderRequest(
//                userId = userId,
//                productId = productId,
//                count = 1
//            )
//            repository.createOrder(request)
//        }
//    }
}

