package com.example.uikit

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun CategoryLazy(
    selected: String,
    categories: List<String>,
    onCategoryClick: (String) -> Unit) {
    LazyRow {
        items(categories) { category ->
            CategoryChip(
                categoryName = category,
                isSelected = (category == selected),
                onCategoryClick = { onCategoryClick(category) })
        }
    }
}