package com.example.network

import com.example.network.model.AuthRequest
import com.example.network.model.AuthResponse
import com.example.network.model.CartRequest
import com.example.network.model.CartResponse
import com.example.network.model.OrderRequest
import com.example.network.model.OrderResponse
import com.example.network.model.ProductDescription
import com.example.network.model.ProductSearchList
import com.example.network.model.ProjectList
import com.example.network.model.ProjectRequest
import com.example.network.model.ProjectResponse
import com.example.network.model.Registration
import com.example.network.model.SalesAndNews
import com.example.network.model.TokenResponse
import com.example.network.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface UserAPI {
    @POST("collections/users/records")
    suspend fun createUser(@Body registration: Registration): Response<User>

    @GET("collections/users/records/{id_user}")
    suspend fun getUser(@Path("id_user") id: String): Response<User>

    @PUT("collections/users/records/{id_user}")
    suspend fun updateUser(@Path("id_user") id: String): Response<User>

    @POST("collections/users/auth-with-password")
    suspend fun authUser(@Body request: AuthRequest): Response<AuthResponse>

    @GET("collections/_authOrigins/records")
    suspend fun getToken(): Response<TokenResponse>

    @DELETE("collections/_authOrigins/records/{id_token}")
    suspend fun deleteUser(@Path("id_token") id: String, @Header("Authorization") token: String)

    @GET("collections/news/records")
    suspend fun seeSales(): Response<SalesAndNews>

    @GET("collections/products/records")
    suspend fun productList(@Header("Authorization") token: String,
                            @Query("filter") filter: String?=null): Response<ProductSearchList>

    @GET("collections/products/records/{id_product}")
    suspend fun productDescription(@Path("id_product") idProduct: String): Response<ProductDescription>

    @GET("collections/project/records")
    suspend fun projectList(): Response<ProjectList>

    @POST("collections/project/records")
    suspend fun createProject(@Body request: ProjectRequest): Response<ProjectResponse>

    @POST("collections/cart/records")
    suspend fun createCart(@Body request: CartRequest): Response<CartResponse>

    @PUT("collections/cart/records/{id_bucket}")
    suspend fun updateCart(@Path("id_bucket") id: String): Response<CartResponse>

    @POST("collections/orders/records")
    suspend fun createOrder(@Body request: OrderRequest): Response<OrderResponse>
}


//    @GET("collections/cart/records")
//    suspend fun getCartItemsForUser(@Query("filter") filter: String): Response<ListResponse<CartResponse>> // Нужен класс ListResponse
//
//    // PUT запрос на обновление количества
//    @PUT("collections/cart/records/{id_bucket}")
//    suspend fun updateCartItem(@Path("id_bucket") id: String, @Body request: UpdateCartQuantityRequest): Response<CartResponse>
//
//    // DELETE запрос на удаление
//    @DELETE("collections/cart/records/{id_bucket}")
//    suspend fun deleteCartItem(@Path("id_bucket") id: String): Response<Unit>
//    }
