package com.example.financemoney.finance

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.financemoney.ui.theme.BackgroundDark
import com.example.financemoney.ui.theme.ErrorRed
import com.example.financemoney.ui.theme.GoldPrimary
import com.example.financemoney.ui.theme.InfoBlue
import com.example.financemoney.ui.theme.SuccessGreen
import com.example.financemoney.ui.theme.SurfaceDark

@Composable
fun ProfileScreen() {
    // TODO: Keep profile data hardcoded for now; Firebase hookup will be added later.
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundDark)
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(Modifier.height(32.dp))
        Text("Settings", style = MaterialTheme.typography.headlineLarge)

        Spacer(Modifier.height(24.dp))

        // User Profile Card
        Surface(
            color = SurfaceDark,
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = "https://picsum.photos/seed/john/200/200",
                    contentDescription = null,
                    modifier = Modifier.size(64.dp).clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(Modifier.width(16.dp))
                Column(Modifier.weight(1f)) {
                    Text("John Doe", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Text("john.doe@gmail.com", color = Color.Gray, fontSize = 14.sp)
                }
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(containerColor = GoldPrimary.copy(alpha = 0.1f)),
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(horizontal = 12.dp)
                ) {
                    Text("Log Out", color = GoldPrimary, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }
        }

        Spacer(Modifier.height(32.dp))

        // Settings Groups
        SettingsGroup("Notifications") {
            SettingsItem("Push Notifications", Icons.Default.Notifications, GoldPrimary, true)
            SettingsItem("Email Reports", Icons.Default.Email, Color.Gray, false)
        }

        Spacer(Modifier.height(24.dp))

        SettingsGroup("Appearance") {
            SettingsItem("App Theme", Icons.Default.Palette, InfoBlue, hasChevron = true, value = "Gold")
        }

        Spacer(Modifier.height(24.dp))

        SettingsGroup("Data & Privacy") {
            SettingsItem("Export Data (CSV)", Icons.Default.Download, SuccessGreen, hasChevron = true)
            SettingsItem("Delete Account", Icons.Default.Delete, ErrorRed)
        }

        Spacer(Modifier.height(48.dp))
        Text(
            "Version 2.4.0 (Build 1042)",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = Color.DarkGray,
            fontSize = 11.sp
        )
    }
}

@Composable
fun SettingsGroup(title: String, content: @Composable ColumnScope.() -> Unit) {
    Column {
        Text(
            title.uppercase(),
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
        )
        Surface(
            color = SurfaceDark,
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(content = content) {
                // Nội dung các item sẽ nằm ở đây
            }
        }
    }
}

@Composable
fun SettingsItem(
    label: String,
    icon: ImageVector,
    iconColor: Color,
    isToggle: Boolean? = null,
    hasChevron: Boolean = false,
    value: String? = null
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier.size(32.dp),
            color = if (iconColor == Color.Gray) Color.DarkGray else iconColor.copy(alpha = 0.2f),
            shape = RoundedCornerShape(8.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(icon, null, tint = iconColor, modifier = Modifier.size(18.dp))
            }
        }
        Spacer(Modifier.width(16.dp))
        Text(label, modifier = Modifier.weight(1f), fontSize = 15.sp)

        if (value != null) {
            Text(value, color = Color.Gray, fontSize = 14.sp, modifier = Modifier.padding(end = 8.dp))
        }

        when {
            isToggle != null -> {
                Switch(
                    checked = isToggle,
                    onCheckedChange = {},
                    colors = SwitchDefaults.colors(checkedThumbColor = Color.White, checkedTrackColor = GoldPrimary)
                )
            }
            hasChevron -> {
                Icon(Icons.Default.ChevronRight, null, tint = Color.DarkGray)
            }
        }
    }
}
