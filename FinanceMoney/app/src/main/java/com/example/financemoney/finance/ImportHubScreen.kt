package com.example.financemoney.finance

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financemoney.ui.theme.BackgroundDark
import com.example.financemoney.ui.theme.ErrorRed
import com.example.financemoney.ui.theme.GoldPrimary
import com.example.financemoney.ui.theme.SuccessGreen
import com.example.financemoney.ui.theme.SurfaceDark

@Composable
fun ImportHubScreen(onBack: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().background(BackgroundDark)) {
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack, null, tint = GoldPrimary) }
            Text("Help", color = GoldPrimary, fontSize = 14.sp)
        }

        Text("Import Hub", style = MaterialTheme.typography.headlineLarge, modifier = Modifier.padding(horizontal = 16.dp))

        Spacer(Modifier.height(16.dp))

        // Segmented Control
        Surface(modifier = Modifier.fillMaxWidth().padding(16.dp).height(44.dp), color = Color(0xFF493F22).copy(alpha = 0.5f), shape = RoundedCornerShape(12.dp)) {
            Row(modifier = Modifier.padding(4.dp)) {
                Box(Modifier.weight(1f).fillMaxHeight(), contentAlignment = Alignment.Center) { Text("Manual", color = Color.Gray) }
                Box(Modifier.weight(1f).fillMaxHeight().background(Color(0xFF231E10), RoundedCornerShape(8.dp)), contentAlignment = Alignment.Center) { Text("Excel", fontWeight = FontWeight.Bold) }
                Box(Modifier.weight(1f).fillMaxHeight(), contentAlignment = Alignment.Center) { Text("Receipt", color = Color.Gray) }
            }
        }

        Column(modifier = Modifier.padding(16.dp).verticalScroll(rememberScrollState())) {
            // Upload Dropzone
            Surface(
                modifier = Modifier.fillMaxWidth().height(200.dp).clickable {},
                color = SurfaceDark.copy(alpha = 0.3f),
                shape = RoundedCornerShape(24.dp),
                border = BorderStroke(2.dp, Color(0xFF685A31))
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                    Icon(Icons.Default.CloudUpload, null, tint = GoldPrimary, modifier = Modifier.size(48.dp))
                    Spacer(Modifier.height(16.dp))
                    Text("Upload Excel File", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Text("Max size: 10MB", color = Color.Gray, fontSize = 12.sp)
                }
            }

            Spacer(Modifier.height(24.dp))
            Text("Recent Imports", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(Modifier.height(12.dp))

            // List Items (Demo)
            ImportItem("Expenses_Oct.xlsx", "Today • 10:42 AM", Icons.Default.Description, SuccessGreen)
            ImportItem("Q3_Budget.csv", "Oct 12 • 4:15 PM", Icons.Default.Description, SuccessGreen)
            ImportItem("Travel_2023.xlsx", "Failed Oct 10", Icons.Default.Error, ErrorRed)
        }
    }
}

@Composable
fun ImportItem(name: String, date: String, icon: ImageVector, statusColor: Color) {
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp), verticalAlignment = Alignment.CenterVertically) {
        Surface(modifier = Modifier.size(44.dp), color = statusColor.copy(alpha = 0.1f), shape = RoundedCornerShape(12.dp)) {
            Box(contentAlignment = Alignment.Center) { Icon(icon, null, tint = statusColor, modifier = Modifier.size(20.dp)) }
        }
        Spacer(Modifier.width(16.dp))
        Column(Modifier.weight(1f)) {
            Text(name, fontWeight = FontWeight.Bold)
            Text(date, color = Color.Gray, fontSize = 12.sp)
        }
        Icon(Icons.Default.ChevronRight, null, tint = Color.Gray)
    }
}
