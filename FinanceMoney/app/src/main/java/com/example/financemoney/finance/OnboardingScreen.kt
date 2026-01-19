package com.example.financemoney.finance

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage // Giả định dùng Coil để load ảnh từ URL
import com.example.financemoney.ui.theme.BackgroundDark
import com.example.financemoney.ui.theme.GoldPrimary
import com.example.financemoney.ui.theme.SeparatorDark

@Composable
fun OnboardingScreen(onContinue: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundDark)
    ) {
        // Top Image Section
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            AsyncImage(
                model = "https://picsum.photos/seed/finance/800/1000",
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(32.dp))
            )
            // Gradient Overlay
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            listOf(Color.Transparent, BackgroundDark.copy(alpha = 0.5f))
                        )
                    )
            )
        }

        // Bottom Content Section
        Surface(
            color = BackgroundDark,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
        ) {
            Column(
                modifier = Modifier.padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Page Indicator
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Box(Modifier.size(width = 32.dp, height = 8.dp).background(GoldPrimary, RoundedCornerShape(4.dp)))
                    Box(Modifier.size(8.dp).background(SeparatorDark, RoundedCornerShape(4.dp)))
                    Box(Modifier.size(8.dp).background(SeparatorDark, RoundedCornerShape(4.dp)))
                }

                Spacer(Modifier.height(24.dp))

                Text(
                    "Track expenses with ease",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    lineHeight = 40.sp
                )

                Spacer(Modifier.height(12.dp))

                Text(
                    "Take control of your finances by monitoring your spending habits in real-time.",
                    color = Color.Gray,
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp
                )

                Spacer(Modifier.height(40.dp))

                Button(
                    onClick = onContinue,
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = GoldPrimary),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text("Get Started", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }

                Spacer(Modifier.height(16.dp))

                TextButton(onClick = {}) {
                    Text("Learn more", color = Color.Gray, fontSize = 14.sp)
                }

                Spacer(Modifier.height(16.dp))
            }
        }
    }
}
