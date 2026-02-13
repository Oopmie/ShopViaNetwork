package com.example.uikit

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uikit.ui.theme.Black
import com.example.uikit.ui.theme.InputBg
import com.example.uikit.ui.theme.White

@Composable
fun CounterButton(
    initialCount: Int = 1,
    onCountChange: (Int) -> Unit
) {
    var count by remember { mutableIntStateOf(initialCount) }
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = InputBg
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(5.dp)
                .height(30.dp)
        ) {
            Text(
                text = "${count} штук",
                modifier = Modifier.padding(horizontal = 8.dp),
                fontSize = 16.sp,
                color = Black
            )
            Button(
                onClick = {
                    if (count > 1) {
                        count--
                        onCountChange(count)
                    }
                }, modifier = Modifier
                    .width(30.dp)
                    .height(30.dp), colors = ButtonColors(
                    containerColor = White, contentColor = Black,
                    disabledContainerColor = White, disabledContentColor = Black
                ), contentPadding = PaddingValues(0.dp)
            ) {
                Text("-", fontSize = 20.sp, color = Color(0xFF939396))
            }
            VerticalSpacer(thickness = 1.dp)
            Button(
                onClick = {
                    count++
                    onCountChange(count)
                }, modifier = Modifier
                    .width(30.dp)
                    .height(30.dp), colors = ButtonColors(
                    containerColor = InputBg, contentColor = Black,
                    disabledContainerColor = InputBg, disabledContentColor = Black
                ), contentPadding = PaddingValues(0.dp)
            ) {
                Text("+", fontSize = 20.sp, color = Color(0xFF939396))
            }
        }
    }
}