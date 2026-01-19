package com.example.financemoney.finance

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Backspace
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.financemoney.ui.theme.BackgroundDark
import com.example.financemoney.ui.theme.GoldPrimary
import com.example.financemoney.ui.theme.SurfaceDark


@Composable
fun AddTransactionScreen(onClose: () -> Unit) {
    var amount by remember { mutableStateOf("0") }

    Column(modifier = Modifier.fillMaxSize().background(BackgroundDark)) {
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Cancel", color = Color.Gray, modifier = Modifier.clickable { onClose() })
            Text("New Transaction", fontWeight = FontWeight.Bold)
            Text("Save", color = GoldPrimary, fontWeight = FontWeight.Bold)
        }

        Column(modifier = Modifier.fillMaxWidth().padding(vertical = 48.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Amount", color = Color.Gray)
            Row(verticalAlignment = Alignment.Bottom) {
                Text("$", fontSize = 32.sp, color = Color.Gray, modifier = Modifier.padding(bottom = 8.dp))
                Text(amount, fontSize = 64.sp, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Numeric Keypad
        Surface(color = Color(0xFF1A160C), modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(8.dp)) {
                val keys = listOf(
                    listOf("1", "2", "3"),
                    listOf("4", "5", "6"),
                    listOf("7", "8", "9"),
                    listOf(".", "0", "DEL")
                )
                keys.forEach { row ->
                    Row(modifier = Modifier.fillMaxWidth()) {
                        row.forEach { key ->
                            KeypadButton(key, modifier = Modifier.weight(1f)) {
                                if (key == "DEL") {
                                    amount = if (amount.length > 1) amount.dropLast(1) else "0"
                                } else if (key == ".") {
                                    if (!amount.contains(".")) amount += "."
                                } else {
                                    amount = if (amount == "0") key else amount + key
                                }
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun KeypadButton(key: String, modifier: Modifier, onClick: () -> Unit) {
    Box(
        modifier = modifier.height(64.dp).padding(4.dp).background(SurfaceDark, RoundedCornerShape(8.dp)).clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        if (key == "DEL") {
            Icon(Icons.Default.Backspace, null, tint = Color.White)
        } else {
            Text(key, fontSize = 24.sp, fontWeight = FontWeight.SemiBold)
        }
    }
}
