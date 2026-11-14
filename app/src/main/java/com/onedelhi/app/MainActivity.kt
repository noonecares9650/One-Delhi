package com.onedelhi.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.onedelhi.app.ui.screens.*
import com.onedelhi.app.ui.theme.OneColors
import com.onedelhi.app.ui.theme.OneDelhiTheme

enum class Tab(val label: String, val icon: ImageVector) {
    Bus("Bus", Icons.Filled.DirectionsBus),
    Tickets("Tickets", Icons.Filled.ConfirmationNumber),
    Hub("Hub", Icons.Filled.EvStation),
    TripPlan("Trip Plan", Icons.Filled.AltRoute),
    Help("Help", Icons.Filled.Help)
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { OneDelhiTheme { OneDelhiApp() } }
    }
}

@Composable
fun OneDelhiApp() {
    var tab by remember { mutableStateOf(Tab.Bus) }
    Column(Modifier.fillMaxSize().statusBarsPadding().background(Color.White)) {
        Box(Modifier.weight(1f)) {
            when (tab) {
                Tab.Bus -> BusScreen()
                Tab.Tickets -> TicketsScreen()
                Tab.Hub -> PlaceholderScreen("Hub")
                Tab.TripPlan -> PlaceholderScreen("Trip Plan")
                Tab.Help -> PlaceholderScreen("Help")
            }
        }
        NavigationBar(containerColor = Color.White, tonalElevation = 0.dp) {
            Tab.values().forEach { t ->
                val selected = t == tab
                NavigationBarItem(
                    selected = selected,
                    onClick = { tab = t },
                    icon = { Icon(t.icon, contentDescription = t.label) },
                    label = {
                        Text(t.label, fontSize = 13.sp,
                            fontWeight = if (selected) FontWeight.Medium else FontWeight.Normal)
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = OneColors.Red,
                        selectedTextColor = Color.Black,
                        unselectedIconColor = Color(0xFF444444),
                        unselectedTextColor = Color(0xFF444444),
                        indicatorColor = Color.Transparent
                    )
                )
            }
        }
        Box(
            Modifier.fillMaxWidth().background(OneColors.Footer).padding(vertical = 4.dp),
            contentAlignment = Alignment.Center
        ) {
            Text("Powered by DTC, DoT & ARF IIT Kanpur", color = Color.White, fontSize = 12.sp)
        }
    }
}
