package com.example.financemoney.finance

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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

data class ReceiptLineItem(
    val name: String,
    val detail: String,
    val amount: String
)

@Composable
fun ReviewReceiptScreen(onBack: () -> Unit, onConfirm: () -> Unit) {
    val lineItems = listOf(
        ReceiptLineItem("Latte", "1 x $4.50", "$4.50"),
        ReceiptLineItem("Blueberry Muffin", "2 x $2.25", "$4.50"),
        ReceiptLineItem("Tip", "Suggested 10%", "$0.90")
    )

    Column(modifier = Modifier.fillMaxSize().background(BackgroundDark)) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(Icons.Default.ArrowBack, null, tint = GoldPrimary)
            }
            Text("Review Receipt", style = MaterialTheme.typography.titleLarge)
            Icon(Icons.Default.CheckCircle, null, tint = GoldPrimary)
        }

        Surface(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            color = SurfaceDark,
            shape = RoundedCornerShape(20.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Starbucks Downtown", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text("Oct 18 • 9:32 AM", color = Color.Gray, fontSize = 12.sp)
                Spacer(modifier = Modifier.height(12.dp))
                Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                    Text("Category", color = Color.Gray, fontSize = 12.sp)
                    Text("Food & Drink", fontWeight = FontWeight.SemiBold, fontSize = 12.sp)
                }
                Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                    Text("Payment", color = Color.Gray, fontSize = 12.sp)
                    Text("Visa **** 4242", fontWeight = FontWeight.SemiBold, fontSize = 12.sp)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            "Items",
            modifier = Modifier.padding(horizontal = 16.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )

        LazyColumn(
            modifier = Modifier.weight(1f).padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(lineItems) { item ->
                Surface(color = SurfaceDark, shape = RoundedCornerShape(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(item.name, fontWeight = FontWeight.Bold)
                            Text(item.detail, color = Color.Gray, fontSize = 12.sp)
                        }
                        Text(item.amount, fontWeight = FontWeight.SemiBold)
                    }
                }
            }
        }

        Surface(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            color = SurfaceDark,
            shape = RoundedCornerShape(18.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text("Total", color = Color.Gray, fontSize = 12.sp)
                    Text("$9.90", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                }
                Button(
                    onClick = onConfirm,
                    colors = ButtonDefaults.buttonColors(containerColor = GoldPrimary),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.height(48.dp)
                ) {
                    Text("Confirm", color = Color.Black, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
