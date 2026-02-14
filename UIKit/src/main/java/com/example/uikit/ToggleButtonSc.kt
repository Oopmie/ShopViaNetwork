package com.example.uikit

import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.uikit.ui.theme.Accent
import com.example.uikit.ui.theme.AccentInactive
import com.example.uikit.ui.theme.White

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun ToggleButtonSc() {
    val checkedState = remember { mutableStateOf(true) }
    Switch(
        checked = checkedState.value,
        onCheckedChange = { checkedState.value = it },
        colors = SwitchDefaults.colors(checkedThumbColor = White,
            checkedTrackColor = Accent,
            uncheckedThumbColor = White,
            uncheckedTrackColor = AccentInactive,
            uncheckedBorderColor = AccentInactive
        )
    )
}