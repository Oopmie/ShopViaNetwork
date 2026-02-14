package com.example.myapplication55

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.myapplication55.presentation.project.CreateProject
import com.example.myapplication55.ui.theme.MyApplication55Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplication55Theme() {
                CreateProject()
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