package com.example.uikit

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class BottomNavItemData(
    val route: String,
    val title: String,
    val iconRes: Int
)

//навигационное меню снизу экрана, переключает по нажатию между страницами
@Composable
fun AppBottomBar(
    items: List<BottomNavItemData>,
    currentRoute: String?,
    onItemClick: (BottomNavItemData) -> Unit
) {
    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 8.dp
    ) {
        items.forEach { item ->
            val isSelected = currentRoute == item.route

            NavigationBarItem(
                selected = isSelected,
                onClick = { onItemClick(item) },
                label = {
                    Text(
                        text = item.title,
                        style = TextStyle(fontSize = 12.sp)
                    )
                },
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconRes),
                        contentDescription = item.title,
                        modifier = Modifier.size(24.dp)
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFF007AFF),
                    selectedTextColor = Color(0xFF007AFF),
                    unselectedIconColor = Color(0xFF939396),
                    unselectedTextColor = Color(0xFF939396),
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}