package com.example.myapplication55.data

import com.example.network.model.AuthRequest
import com.example.network.model.AuthResponse
import com.example.network.model.CartResponse
import com.example.network.model.OrderRequest
import com.example.network.model.OrderResponse
import com.example.network.model.Registration
import com.example.network.model.SearchList
import com.example.network.model.User

interface AppRepository {
        // Auth
        suspend fun register(registration: Registration): Result<User>
        suspend fun login(request: AuthRequest): Result<AuthResponse>

        // Products and Cart
        suspend fun getProducts(query: String = ""): Result<List<SearchList>>
        suspend fun addToCart(productId: String, userId: String): Result<CartResponse>

        // Orders
        suspend fun createOrder(request: OrderRequest): Result<OrderResponse>
    }