package com.example.uikit

import android.service.autofill.OnClickAction
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uikit.ui.theme.Black
import com.example.uikit.ui.theme.CaptionColor
import com.example.uikit.ui.theme.InputStroke
import com.example.uikit.ui.theme.Text
import com.example.uikit.ui.theme.White

//осуществление входа через вк или яндекс с их иконками на кнопках
@Composable
fun Login(
    imageRes1: Int,
    imageRes2: Int
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(190.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            "Или войдите с помощью", style = Text.bodySmall, color = CaptionColor,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            {}, modifier = Modifier
                .fillMaxWidth()
                .height(65.dp), shape = RoundedCornerShape(12.dp),
            colors = ButtonColors(
                containerColor = White,
                contentColor = Black,
                disabledContentColor = Black,
                disabledContainerColor = White
            ),
            border = BorderStroke(1.dp, color = InputStroke)
        ) {
            Image(
                bitmap = ImageBitmap.imageResource(imageRes1), contentDescription = "vk",
                modifier = Modifier.size(35.dp)
            )
            Text("      Войти с VK", fontSize = 17.sp)
        }
        Button(
            {}, modifier = Modifier
                .fillMaxWidth()
                .height(65.dp), shape = RoundedCornerShape(12.dp),
            colors = ButtonColors(
                containerColor = White,
                contentColor = Black,
                disabledContentColor = Black,
                disabledContainerColor = White
            ),
            border = BorderStroke(1.dp, color = InputStroke)
        ) {
            Image(
                bitmap = ImageBitmap.imageResource(imageRes2), contentDescription = "yandex",
                modifier = Modifier.size(35.dp)
            )
            Text("      Войти с Yandex", fontSize = 17.sp)
        }
    }
}