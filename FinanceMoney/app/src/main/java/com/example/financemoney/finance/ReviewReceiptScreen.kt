package com.example.financemoney.finance

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TransactionsScreen() {
    val groups = listOf(
        TransactionGroup("Today", "-$45.00", emptyList()), // Placeholder items
        TransactionGroup("Yesterday", "-$12.00", emptyList())
    )

    Column(modifier = Modifier.fillMaxSize().background(BackgroundDark)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Spacer(modifier = Modifier.height(32.dp))
            Text("Transactions", style = MaterialTheme.typography.headlineLarge)
            Spacer(modifier = Modifier.height(16.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                TextField(
                    value = "",
                    onValueChange = {},
                    placeholder = { Text("Search") },
                    leadingIcon = { Icon(Icons.Default.Search, null, tint = Color.Gray) },
                    modifier = Modifier.weight(1f).height(48.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = SurfaceDark,
                        unfocusedContainerColor = SurfaceDark,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(12.dp)
                )
                Box(modifier = Modifier.size(48.dp).background(SurfaceDark, RoundedCornerShape(12.dp)), contentAlignment = Alignment.Center) {
                    Icon(Icons.Default.Settings, null, tint = GoldPrimary)
                }
            }
        }

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            groups.forEach { group ->
                stickyHeader {
                    Row(
                        modifier = Modifier.fillMaxWidth().background(BackgroundDark.copy(alpha = 0.9f)).padding(horizontal = 16.dp, vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Text(group.title, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                        Text(group.total, color = Color.Gray, fontSize = 14.sp)
                    }
                }
                // Items would go here...
            }
        }
    }
}
