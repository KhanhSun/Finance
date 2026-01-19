package com.example.financemoney.finance

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financemoney.ui.theme.BackgroundDark
import com.example.financemoney.ui.theme.GoldPrimary
import com.example.financemoney.ui.theme.SurfaceDark

@Composable
fun InsightsScreen() {
    Column(
        modifier = Modifier.fillMaxSize().background(BackgroundDark).padding(20.dp).verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Text("Insights", style = MaterialTheme.typography.headlineLarge)

        Spacer(modifier = Modifier.height(24.dp))

        // Forecasting Card
        Surface(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(24.dp), color = SurfaceDark) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text("FUTURE TREND", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
                Text("Proj. $3,200", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Text("Next Month Forecast", color = GoldPrimary, fontSize = 12.sp)

                Spacer(modifier = Modifier.height(16.dp))

                Canvas(modifier = Modifier.fillMaxWidth().height(100.dp)) {
                    val path = Path().apply {
                        moveTo(0f, size.height * 0.8f)
                        cubicTo(size.width * 0.2f, size.height * 0.9f, size.width * 0.4f, size.height * 0.2f, size.width * 0.6f, size.height * 0.4f)
                        cubicTo(size.width * 0.8f, size.height * 0.6f, size.width * 0.9f, 0f, size.width, size.height * 0.1f)
                    }
                    drawPath(path, GoldPrimary, style = Stroke(3.dp.toPx()))

                    val fillPath = Path().apply {
                        addPath(path)
                        lineTo(size.width, size.height)
                        lineTo(0f, size.height)
                        close()
                    }
                    drawPath(fillPath, brush = Brush.verticalGradient(listOf(GoldPrimary.copy(alpha = 0.3f), Color.Transparent)))
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text("Detected Anomalies", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        AnomalyItem("Netflix Subscription", "Double Charge • Today", "-$15.99", "HIGH", Color.Red)
        Spacer(modifier = Modifier.height(12.dp))
        AnomalyItem("Starbucks Coffee", "Unusual Location • Yesterday", "-$6.50", "LOW", Color.Cyan)
    }
}

@Composable
fun AnomalyItem(title: String, desc: String, amount: String, level: String, color: Color) {
    Surface(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(20.dp), color = SurfaceDark) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier.size(48.dp).background(color.copy(alpha = 0.1f), CircleShape), contentAlignment = Alignment.Center) {
                Icon(Icons.Default.TrendingUp, null, tint = color)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(title, fontWeight = FontWeight.Bold)
                Text(desc, color = Color.Gray, fontSize = 12.sp)
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(amount, fontWeight = FontWeight.Bold)
                Surface(color = color.copy(alpha = 0.1f), shape = RoundedCornerShape(4.dp)) {
                    Text(level, color = color, fontSize = 10.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 4.dp))
                }
            }
        }
    }
}
