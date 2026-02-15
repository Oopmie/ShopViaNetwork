package com.example.myapplication55.di

import com.example.myapplication55.viewModel.ProductViewModel
import com.example.myapplication55.data.SessionManager
import com.example.myapplication55.viewModel.AuthViewModel
import com.example.network.UserAPI
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModules= module {
    single { Dispatchers.IO }
    single { SessionManager(get()) }
    single { OkHttpClient.Builder().build() }
    single {
        Retrofit.Builder().baseUrl("https://api.matule.ru/").
        addConverterFactory(GsonConverterFactory.create()).build()
    }
    single { get<Retrofit>().create(UserAPI::class.java) }
    viewModel { AuthViewModel(get(), get()) }
    viewModel { ProductViewModel(get(), get()) }
}