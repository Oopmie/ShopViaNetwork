package com.example.network

import com.example.network.model.AuthRequest
import com.example.network.model.AuthResponse
import com.example.network.model.CartRequest
import com.example.network.model.CartResponce
import com.example.network.model.OrderRequest
import com.example.network.model.OrderResponce
import com.example.network.model.ProductDescription
import com.example.network.model.ProductSearchList
import com.example.network.model.ProjectList
import com.example.network.model.ProjectRequest
import com.example.network.model.ProjectResponce
import com.example.network.model.Registration
import com.example.network.model.SalesAndNews
import com.example.network.model.TokenResponse
import com.example.network.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface UserAPI {
    @POST("collections/users/records")
    suspend fun createUser(@Body registration: Registration): Response<User>

    @GET("collections/users/records/{id_user}")
    suspend fun getUser(@Query("id_user") id: String): Response<User>

    @PUT("collections/users/records/{id_user}")
    suspend fun updateUser(@Query("id_user") id: String): Response<User>

    @GET("collections/users/auth-with-password")
    suspend fun authUser(@Body request: AuthRequest): Response<AuthResponse>

    @GET("collections/_authOrigins/records")
    suspend fun getToken(): Response<TokenResponse>

//    @DELETE("collections/_authOrigins/records/{id_token}")
//    suspend fun deleteUser(@Query("token")token: String)

    @GET("collections/news/records")
    suspend fun seeSales(): Response<SalesAndNews>

    @GET("collections/products/records")
    suspend fun productList(@Query("title")title: String): Response<ProductSearchList>

    @GET("collections/products/records/{id_product}")
    suspend fun productDescription(@Query("id_product")idProduct: String): Response<ProductDescription>

    @GET("collections/project/records")
    suspend fun projectList(): Response<ProjectList>

    @POST("collections/project/records")
    suspend fun createProject(@Body request: ProjectRequest): Response<ProjectResponce>

    @POST("collections/cart/records")
    suspend fun createCart(@Body request: CartRequest): Response<CartResponce>

    @PUT("collections/cart/records/{id_bucket}")
    suspend fun updateCart(@Query("id_bucket")id: String): Response<CartResponce>

    @POST("collections/orders/records")
    suspend fun createOrder(@Body request: OrderRequest): Response<OrderResponce>

}