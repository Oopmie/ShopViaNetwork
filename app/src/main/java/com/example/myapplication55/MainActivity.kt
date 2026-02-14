package com.example.myapplication55

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication55.presentation.auth.CreateAccount
import com.example.myapplication55.test.CardInCartScreen
import com.example.myapplication55.ui.theme.MyApplication55Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplication55Theme() {
                CardInCartScreen()
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    MyApplication55Theme {
//        CardInCartScreen()
//    }
//}