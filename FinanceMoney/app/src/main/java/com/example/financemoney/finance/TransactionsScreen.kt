
package com.example.financemoney.finance

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financemoney.ui.theme.BackgroundDark
import com.example.financemoney.ui.theme.GoldPrimary
import com.example.financemoney.ui.theme.SurfaceDark

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TransactionsScreen(onNavigate: (Screen) -> Unit) {
    val groups = listOf(
        TransactionGroup(
            "Today", "-$45.00", listOf(
                TransactionItem(
                    "1",
                    "Starbucks",
                    "Food & Drink • 9:41 AM",
                    "-$5.50",
                    Icons.Default.Coffee,
                    Color(0xFFF59E0B)
                ),
                TransactionItem(
                    "2",
                    "Whole Foods",
                    "Groceries • 12:30 PM",
                    "-$39.50",
                    Icons.Default.ShoppingCart,
                    Color(0xFF10B981)
                )
            )
        ),
        TransactionGroup(
            "Yesterday", "-$12.00", listOf(
                TransactionItem(
                    "3",
                    "AMC Theaters",
                    "Entertainment • 8:15 PM",
                    "-$12.00",
                    Icons.Default.Movie,
                    Color(0xFFA855F7)
                )
            )
        )
    )

    Column(modifier = Modifier.fillMaxSize().background(BackgroundDark)) {
        headerSection(onNavigate)

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            groups.forEach { group ->
                stickyHeader {
                    Row(
                        modifier = Modifier.fillMaxWidth().background(BackgroundDark.copy(alpha = 0.95f)).padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Text(group.title, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                        Text(group.total, color = Color.Gray, fontSize = 14.sp)
                    }
                }
                items(group.items) { item ->
                    TransactionRow(item)
                }
            }
        }
    }
}

@Composable
fun headerSection(onNavigate: (Screen) -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Spacer(Modifier.height(16.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            IconButton(onClick = { onNavigate(Screen.ADD_TRANSACTION) }, modifier = Modifier.background(GoldPrimary.copy(alpha = 0.1f), CircleShape)) {
                Icon(Icons.Default.Add, null, tint = GoldPrimary)
            }
        }
        Text("Transactions", style = MaterialTheme.typography.headlineLarge)
        Spacer(Modifier.height(12.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            TextField(
                value = "", onValueChange = {}, placeholder = { Text("Search") },
                leadingIcon = { Icon(Icons.Default.Search, null, tint = Color.Gray) },
                modifier = Modifier.weight(1f).height(52.dp),
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.colors(focusedContainerColor = SurfaceDark, unfocusedContainerColor = SurfaceDark, unfocusedIndicatorColor = Color.Transparent)
            )
            Surface(modifier = Modifier.size(52.dp), color = SurfaceDark, shape = RoundedCornerShape(12.dp)) {
                Box(contentAlignment = Alignment.Center) { Icon(Icons.Default.Tune, null, tint = GoldPrimary) }
            }
        }
    }
}

@Composable
fun TransactionRow(item: TransactionItem) {
    Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp), verticalAlignment = Alignment.CenterVertically) {
        Surface(modifier = Modifier.size(40.dp), color = item.iconColor.copy(alpha = 0.1f), shape = RoundedCornerShape(10.dp)) {
            Box(contentAlignment = Alignment.Center) { Icon(item.icon, null, tint = item.iconColor, modifier = Modifier.size(20.dp)) }
        }
        Spacer(Modifier.width(16.dp))
        Column(Modifier.weight(1f)) {
            Text(item.merchant, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(item.description, color = Color.Gray, fontSize = 13.sp)
        }
        Text(item.amount, fontWeight = FontWeight.Medium, fontSize = 16.sp)
    }
}
