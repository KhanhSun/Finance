package com.example.financemoney.finance

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

enum class Screen {
    SPLASH,
    ONBOARDING,
    AUTH,
    SETUP,
    DASHBOARD,
    TRANSACTIONS,
    INSIGHTS,
    PROFILE,
    ADD_TRANSACTION,
    SCANNER,
    IMPORT,
    REVIEW_RECEIPT
}

data class TransactionItem(
    val id: String,
    val merchant: String,
    val description: String,
    val amount: String,
    val icon: ImageVector,
    val iconColor: Color,
    val isPositive: Boolean = false
)

data class TransactionGroup(
    val title: String,
    val total: String,
    val items: List<TransactionItem>
)

data class Account(
    val id: String,
    val name: String,
    val description: String,
    val icon: String,
    val color: Color,
    val enabled: Boolean
)
