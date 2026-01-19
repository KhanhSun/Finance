
package com.example.financemoney.finance

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.financemoney.ui.theme.GoldPrimary

@Composable
fun ScannerScreen(onCancel: () -> Unit, onScan: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize().background(Color.Black)) {
        // Camera Preview simulation
        AsyncImage(
            model = "https://picsum.photos/seed/receipt/800/1200",
            contentDescription = null,
            modifier = Modifier.fillMaxSize().alpha(0.6f),
            contentScale = ContentScale.Crop
        )

        Column(modifier = Modifier.fillMaxSize()) {
            // Header
            Row(modifier = Modifier.fillMaxWidth().padding(24.dp).padding(top = 16.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                TextButton(onClick = onCancel) { Text("Cancel", color = Color.White) }
                Surface(color = Color.Black.copy(alpha = 0.4f), shape = RoundedCornerShape(20.dp)) {
                    Row(Modifier.padding(horizontal = 12.dp, vertical = 4.dp), verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.AutoFixHigh, null, tint = GoldPrimary, modifier = Modifier.size(14.dp))
                        Spacer(Modifier.width(4.dp))
                        Text("AUTO", color = Color.White, fontSize = 10.sp, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
                    }
                }
            }

            Spacer(Modifier.weight(1f))

            // Scanner Frame
            Box(modifier = Modifier.size(width = 280.dp, height = 360.dp).align(Alignment.CenterHorizontally), contentAlignment = Alignment.Center) {
                // Corners
                Box(Modifier.fillMaxSize()) {
                    Box(Modifier.size(40.dp).align(Alignment.TopStart).background(Color.Transparent).border(4.dp, GoldPrimary, RoundedCornerShape(topStart = 24.dp, topEnd = 0.dp, bottomStart = 0.dp, bottomEnd = 0.dp)))
                    Box(Modifier.size(40.dp).align(Alignment.TopEnd).background(Color.Transparent).border(4.dp, GoldPrimary, RoundedCornerShape(topStart = 0.dp, topEnd = 24.dp, bottomStart = 0.dp, bottomEnd = 0.dp)))
                    Box(Modifier.size(40.dp).align(Alignment.BottomStart).background(Color.Transparent).border(4.dp, GoldPrimary, RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp, bottomStart = 24.dp, bottomEnd = 0.dp)))
                    Box(Modifier.size(40.dp).align(Alignment.BottomEnd).background(Color.Transparent).border(4.dp, GoldPrimary, RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp, bottomStart = 0.dp, bottomEnd = 24.dp)))
                }
            }

            Spacer(Modifier.weight(1f))

            // Controls
            Surface(modifier = Modifier.fillMaxWidth(), color = Color.Black.copy(alpha = 0.5f)) {
                Row(modifier = Modifier.padding(32.dp).fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Box(Modifier.size(48.dp).background(Color.White.copy(alpha = 0.1f), RoundedCornerShape(8.dp)))
                    IconButton(onClick = onScan, modifier = Modifier.size(80.dp).border(4.dp, Color.White, CircleShape)) {
                        Box(Modifier.size(60.dp).background(GoldPrimary, CircleShape))
                    }
                    IconButton(onClick = {}, modifier = Modifier.size(48.dp).background(Color.White.copy(alpha = 0.1f), CircleShape)) {
                        Icon(Icons.Default.FlashOff, null, tint = Color.White)
                    }
                }
            }
        }
    }
}

private fun Modifier.border(width: androidx.compose.ui.unit.Dp, color: Color, shape: androidx.compose.ui.graphics.Shape) = this.then(
    androidx.compose.foundation.border(width, color, shape)
)
