package com.example.financemoney.finance

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.financemoney.ui.theme.SurfaceDark
import com.example.financemoney.ui.theme.BackgroundDark
import com.example.financemoney.ui.theme.GoldPrimary
import com.example.financemoney.ui.theme.FinTrackTheme

@Composable
fun DashboardScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundDark)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            Text("Dashboard", style = MaterialTheme.typography.headlineLarge)
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(SurfaceDark)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Time Filter Tabs
        Surface(
            modifier = Modifier.fillMaxWidth().height(40.dp),
            shape = RoundedCornerShape(8.dp),
            color = SurfaceDark
        ) {
            Row(modifier = Modifier.padding(2.dp)) {
                Box(
                    modifier = Modifier.weight(1f).fillMaxHeight().background(Color(0xFF636366), RoundedCornerShape(6.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text("This Month", fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
                }
                Box(modifier = Modifier.weight(1f).fillMaxHeight(), contentAlignment = Alignment.Center) {
                    Text("Last Month", color = Color.Gray, fontSize = 13.sp)
                }
                Box(modifier = Modifier.weight(1f).fillMaxHeight(), contentAlignment = Alignment.Center) {
                    Text("Year", color = Color.Gray, fontSize = 13.sp)
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Summary Cards
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            StatCard(
                modifier = Modifier.weight(1f),
                title = "TOTAL SPENDING",
                amount = "$1,240.50",
                icon = Icons.Default.CreditCard,
                iconColor = Color.LightGray
            )
            StatCard(
                modifier = Modifier.weight(1f),
                title = "REMAINING",
                amount = "$759.50",
                icon = Icons.Default.AccountBalanceWallet,
                iconColor = GoldPrimary,
                borderColor = GoldPrimary.copy(alpha = 0.2f)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Spending Chart
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            color = SurfaceDark
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Spending by Category", fontWeight = FontWeight.Bold)
                    Text("Details", color = GoldPrimary, fontSize = 14.sp)
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier.size(100.dp), contentAlignment = Alignment.Center) {
                        Canvas(modifier = Modifier.size(100.dp)) {
                            drawArc(Color(0xFFF3BA12), -90f, 126f, false, style = Stroke(20.dp.toPx()))
                            drawArc(Color(0xFFFEC84B), 36f, 90f, false, style = Stroke(20.dp.toPx()))
                            drawArc(Color(0xFF4B5563), 126f, 90f, false, style = Stroke(20.dp.toPx()))
                            drawArc(Color(0xFF9CA3AF), 216f, 54f, false, style = Stroke(20.dp.toPx()))
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("Total", fontSize = 10.sp, color = Color.Gray)
                            Text("$2k", fontWeight = FontWeight.Bold)
                        }
                    }
                    Spacer(modifier = Modifier.width(24.dp))
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        LegendItem("Food", "35%", Color(0xFFF3BA12))
                        LegendItem("Transport", "25%", Color(0xFFFEC84B))
                        LegendItem("Utilities", "25%", Color(0xFF4B5563))
                    }
                }
            }
        }
    }
}

@Composable
fun StatCard(modifier: Modifier, title: String, amount: String, icon: ImageVector, iconColor: Color, borderColor: Color = Color.Transparent) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        color = SurfaceDark,
        border = if (borderColor != Color.Transparent) BorderStroke(1.dp, borderColor) else null
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Box(modifier = Modifier.size(32.dp).background(Color.White.copy(alpha = 0.05f), CircleShape), contentAlignment = Alignment.Center) {
                Icon(icon, null, tint = iconColor, modifier = Modifier.size(18.dp))
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(title, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
            Text(amount, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun LegendItem(label: String, value: String, color: Color) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier.size(8.dp).background(color, CircleShape))
        Spacer(modifier = Modifier.width(8.dp))
        Text(label, fontSize = 12.sp, color = Color.Gray, modifier = Modifier.weight(1f))
        Text(value, fontSize = 12.sp, fontWeight = FontWeight.Bold)
    }
}

@Preview
@Composable
fun DashboardPreview() {
    FinTrackTheme { DashboardScreen() }
}
