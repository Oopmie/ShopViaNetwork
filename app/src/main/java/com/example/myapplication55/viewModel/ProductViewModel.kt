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
import com.example.network.model.OrderRequest
import com.example.network.model.SearchList
import kotlinx.coroutines.launch

class ProductViewModel(
    private val repository: AppRepository,
    private val sessionManager: SessionManager
) : ViewModel() {
    var products by mutableStateOf<List<SearchList>>(emptyList())
        private set
    var isLoading by mutableStateOf(false)
        private set
    var cartItemIds = mutableStateListOf<String>()

    init {
        products = listOf(
            SearchList(
                id = "1", title = "Тест", price = 999, type = "Одежда",
                typeCloses = "хм"
            )
        )
        loadProducts()
    }


    fun loadProducts(query: String = "") {
        viewModelScope.launch {
            isLoading = true
            val result = repository.getProducts(query)

            result.onSuccess { list ->
                products = list
            }.onFailure {
            }
            isLoading = false
        }
    }

    fun toggleCart(productId: String) {
        val currentUserId = sessionManager.getUserId()
        if (currentUserId == null) {
            return
        }
        viewModelScope.launch {
            val result = repository.addToCart(productId, currentUserId)
            result.onSuccess {
                cartItemIds.add(productId)
            }.onFailure {
            }
        }
    }

    fun placeOrder(productId: String) {
        val userId = sessionManager.getUserId()
        if (userId == null) {
            return
        }
        viewModelScope.launch {
            val request = OrderRequest(
                userId = userId,
                productId = productId,
                count = 1
            )
            repository.createOrder(request)
        }
    }
}

