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
import com.example.network.model.ProjectRequest
import com.example.network.model.ProjectResponse
import com.example.network.model.SearchList
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ProductViewModel(
    val api: UserAPI,
    private val sessionManager: SessionManager
) : ViewModel() {
    var products by mutableStateOf<List<SearchList>>(emptyList())
    var description by mutableStateOf<ProductDescription?>(null)
    var cartMap = mutableStateOf<Map<String, Int>>(emptyMap())
        private set
    val addedProductIds = derivedStateOf { cartMap.value.keys }
    var categories by mutableStateOf<List<String>>(listOf("Все"))
    var userProjects by mutableStateOf<List<ProjectResponse>>(emptyList())
        private set
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
        cartMap.value = cartMap.value - productId
    }
    fun loadProducts(category: String) {
        viewModelScope.launch {
            val token = "Bearer ${sessionManager.getToken()}"
            val filter = if (category == "Все") null else "type='$category'"
            val response = api.productList(token, filter)
            if (response.isSuccessful) {
                val newProducts = response.body()?.items ?: emptyList()
                products = newProducts
                if (category == "Все") {
                    val uniqueTypes =
                        newProducts.map { it.type }.distinct().filter { it.isNotBlank() }
                    categories = listOf("Все") + uniqueTypes
                }
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
        viewModelScope.launch {
            description = null
            try {
                val response = api.productDescription(productId)
                if (response.isSuccessful) {
                    description = response.body()
                } else {
                    println("ошибка")
                }
            } catch (e: Exception) {
            }
        }
    }

    fun loadUserProjects() {
        userProjects = sessionManager.getProjects()
    }
    fun saveProject(request: ProjectRequest, onSuccess: () -> Unit) {
        viewModelScope.launch {
            val newProject = ProjectResponse(
                id = System.currentTimeMillis().toString(),
                title = request.title,
                dateStart = request.dateStart,
                dateEnd = request.dateEnd,
                gender = request.gender,
                descriptionSource = request.descriptionSource,
                category = request.category,
                image = request.image,
                userId = request.userId,
                collectionId = "", collectionName = "", created = "", updated = ""
            )
            val updatedList = userProjects + newProject
            userProjects = updatedList
            sessionManager.saveProjects(updatedList)
            try { api.createProject(request) } catch (e: Exception) {}
            onSuccess()
        }
    }
    fun calculateDaysPassed(dateStart: String): String {
        return try {
            val sdf = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
            val startDate = sdf.parse(dateStart)
            val diff = Date().time - startDate!!.time
            val days = diff / (24 * 60 * 60 * 1000)
            if (days < 0) "Будет начат" else "Прошло дней: $days"
        } catch (e: Exception) { "Дата не указана" }
    }
}