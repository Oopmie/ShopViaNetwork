package com.example.uikit

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun CategoryLazy() {
    var selectedCategory by remember { mutableStateOf("Все") }
    LazyRow() {
        val categories = listOf("Все", "Женщинам", "Мужчинам")
        items(categories) { category ->
            CategoryChip(
                categoryName = category,
                isSelected = (category == selectedCategory),
                onCategoryClick = { selectedCategory = category }
            )
        }
    }
}