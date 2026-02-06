package com.example.uikit

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.uikit.ui.theme.Accent

@Composable
fun CategoryChip(
    categoryName: String,
    isSelected: Boolean,
    onCategoryClick: () -> Unit
) {
    Button(
        onClick = onCategoryClick,
        modifier = Modifier
            .padding(end = 8.dp)
            .height(40.dp),
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) Accent else Color(0xFFF4F4F4),
            contentColor = if (isSelected) Color.White else Color.Black
        ),
        border = if (isSelected) null else BorderStroke(1.dp, Color.LightGray),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        Text(text = categoryName, style = MaterialTheme.typography.bodyMedium)
    }
}