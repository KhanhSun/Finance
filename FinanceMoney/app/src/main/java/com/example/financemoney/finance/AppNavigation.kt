package com.example.financemoney.finance

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

import com.example.financemoney.ui.theme.BackgroundDark
import com.example.financemoney.ui.theme.FinTrackTheme
import com.example.financemoney.ui.theme.GoldPrimary

@Composable
fun FinTrackApp() {
    var currentScreen by remember { mutableStateOf(Screen.SPLASH) }
    var showAddDialog by remember { mutableStateOf(false) }

    // Logic chuyển màn hình tự động từ Splash (giống useEffect trong React)
    LaunchedEffect(currentScreen) {
        if (currentScreen == Screen.SPLASH) {
            delay(2500)
            currentScreen = Screen.ONBOARDING
        }
    }

    FinTrackTheme {
        Scaffold(
            bottomBar = {
                // Chỉ hiện BottomBar ở các màn hình chính
                if (currentScreen in listOf(
                        Screen.DASHBOARD,
                        Screen.TRANSACTIONS,
                        Screen.INSIGHTS,
                        Screen.PROFILE
                    )
                ) {
                    NavigationBar(containerColor = BackgroundDark, contentColor = Color.Gray) {
                        NavigationBarItem(
                            selected = currentScreen == Screen.DASHBOARD,
                            onClick = {
                                currentScreen = Screen.DASHBOARD
                            },
                            icon = { Icon(Icons.Default.Dashboard, null) },
                            label = { Text("Home") },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = GoldPrimary,
                                selectedTextColor = GoldPrimary,
                                indicatorColor = Color.Transparent
                            )
                        )
                        NavigationBarItem(
                            selected = currentScreen == Screen.TRANSACTIONS,
                            onClick = {
                                currentScreen = Screen.TRANSACTIONS
                            },
                            icon = { Icon(Icons.Default.Receipt, null) },
                            label = { Text("History") }
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        NavigationBarItem(
                            selected = currentScreen == Screen.INSIGHTS,
                            onClick = {
                                currentScreen = Screen.INSIGHTS
                            },
                            icon = { Icon(Icons.Default.PieChart, null) },
                            label = { Text("Insights") }
                        )
                        NavigationBarItem(
                            selected = currentScreen == Screen.PROFILE,
                            onClick = { currentScreen = Screen.PROFILE },
                            icon = { Icon(Icons.Default.Person, null) },
                            label = { Text("Profile") }
                        )
                    }
                }
            },
            floatingActionButton = {
                if (currentScreen in listOf(
                        Screen.DASHBOARD,
                        Screen.TRANSACTIONS,
                        Screen.INSIGHTS,
                        Screen.PROFILE
                    )
                ) {
                    FloatingActionButton(
                        onClick = { showAddDialog = true },
                        containerColor = GoldPrimary,
                        contentColor = Color.Black,
                        shape = CircleShape,
                        modifier = Modifier.offset(y = 50.dp)
                    ) {
                        Icon(Icons.Default.Add, null)
                    }
                }
            },
            floatingActionButtonPosition = FabPosition.Center
        ) { padding ->
            Box(
                modifier = Modifier.padding(
                    if (currentScreen in listOf(
                            Screen.SPLASH,
                            Screen.ONBOARDING
                        )
                    ) PaddingValues(0.dp) else padding
                )
            ) {
                when (currentScreen) {
                    Screen.SPLASH -> SplashScreen()
                    Screen.ONBOARDING -> OnboardingScreen(onContinue = {
                        currentScreen = Screen.DASHBOARD
                    })

                    Screen.DASHBOARD -> DashboardScreen()
                    Screen.TRANSACTIONS -> TransactionsScreen { screen ->
                        when (screen) {
                            Screen.ADD_TRANSACTION -> showAddDialog = true
                            else -> currentScreen = screen
                        }
                    }
                    Screen.INSIGHTS -> InsightsScreen()
                    Screen.PROFILE -> ProfileScreen() // Sẽ được triển khai sau nếu cần
                    else -> DashboardScreen()
                }
            }
        }

        if (showAddDialog) {
            AddTransactionScreen(onClose = { showAddDialog = false })
        }
    }
}
