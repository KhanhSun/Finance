package com.example.financemoney

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.financemoney.finance.FinTrackApp
import com.example.financemoney.ui.theme.FinTrackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FinTrackTheme {
                FinTrackApp()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    FinTrackTheme { FinTrackApp() }
}
