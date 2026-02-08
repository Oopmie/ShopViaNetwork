package com.example.myapplication55.data

import com.example.network.UserAPI
import com.example.network.model.AuthRequest
import com.example.network.model.CartRequest
import com.example.network.model.CartResponse
import com.example.network.model.OrderRequest
import com.example.network.model.Registration
import com.example.network.model.SearchList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext


class AppRepositoryImpl(
    private val api: UserAPI,
    private val dispatcher: CoroutineDispatcher
) : AppRepository {

    override suspend fun getProducts(query: String): Result<List<SearchList>> = withContext(dispatcher) {
        try {
            val filter = if (query.isNotEmpty()) "title ~ '$query'" else ""
            val response = api.productList(filter)

            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!.items)
            } else {
                Result.failure(Exception("Ошибка сервера: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    override suspend fun addToCart(productId: String, userId: String): Result<CartResponse> = withContext(dispatcher) {
        try {
            val request = CartRequest(userId = userId, productId = productId, count = 1)
            val response = api.createCart(request)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Ошибка корзины: ${response.code()}"))
            }
        } catch (e: Exception) { Result.failure(e) }
    }
//    override suspend fun register(registration: Registration) = withContext(dispatcher) {
//        try {
//            val response = api.createUser(registration)
//            if (response.isSuccessful) Result.success(response.body()!!)
//            else Result.failure(Exception("Ошибка регистрации"))
//        } catch (e: Exception) { Result.failure(e) }
//    }
//
//    override suspend fun login(request: AuthRequest) = withContext(dispatcher) {
//        try {
//            val response = api.authUser(request)
//            if (response.isSuccessful) Result.success(response.body()!!)
//            else Result.failure(Exception("Неверный логин или пароль"))
//        } catch (e: Exception) { Result.failure(e) }
//    }

    override suspend fun createOrder(request: OrderRequest) = withContext(dispatcher) {
        try {
            val response = api.createOrder(request)
            if (response.isSuccessful) Result.success(response.body()!!)
            else Result.failure(Exception("Ошибка оформления заказа"))
        } catch (e: Exception) { Result.failure(e) }
    }

}
