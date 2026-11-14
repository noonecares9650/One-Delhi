package com.onedelhi.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.onedelhi.app.data.BusStop
import com.onedelhi.app.data.MockData
import com.onedelhi.app.ui.theme.OneColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BusScreen() {
    val sheetState = rememberStandardBottomSheetState(
        initialValue = SheetValue.PartiallyExpanded, skipHiddenState = true
    )
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)
    var showBusStops by remember { mutableStateOf(true) }

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 190.dp,
        sheetContainerColor = Color.White,
        sheetShape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
        sheetContent = {
            Column(Modifier.fillMaxWidth().heightIn(min = 500.dp).padding(horizontal = 16.dp)) {
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    StopChip("Bus Stop", showBusStops) { showBusStops = true }
                    StopChip("Metro Stop", !showBusStops) { showBusStops = false }
                }
                Spacer(Modifier.height(8.dp))
                if (showBusStops) {
                    LazyColumn { items(MockData.stops) { StopRow(it) } }
                } else {
                    Text("Nearby metro stops will appear here", color = OneColors.Grey,
                        modifier = Modifier.padding(16.dp))
                }
            }
        }
    ) { padding ->
        Box(Modifier.fillMaxSize().padding(padding)) {
            // TODO: replace with real map (Google Maps Compose / MapLibre)
            Box(Modifier.fillMaxSize().background(Color(0xFFDDEFD8)),
                contentAlignment = Alignment.Center) {
                Text("MAP", color = OneColors.Grey)
            }
            Header(Modifier.align(Alignment.TopCenter))
            FloatingActionButton(
                onClick = { /* TODO: locate me */ },
                containerColor = Color.White, shape = CircleShape,
                modifier = Modifier.align(Alignment.CenterEnd).padding(16.dp)
            ) { Icon(Icons.Filled.MyLocation, null, tint = Color.Black) }
        }
    }
}

@Composable
private fun Header(modifier: Modifier = Modifier) {
    Column(
        modifier.fillMaxWidth()
            .background(
                Brush.horizontalGradient(listOf(OneColors.MaroonDark, OneColors.Maroon, Color(0xFFE5793C))),
                RoundedCornerShape(bottomStart = 36.dp, bottomEnd = 36.dp)
            )
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Delhi Transport\nCorporation", color = Color.White, fontSize = 10.sp)
            Text("ONE", color = Color.White, fontSize = 30.sp, fontWeight = FontWeight.Bold)
            Icon(Icons.Filled.AccountBalance, null, tint = Color.White)
            Icon(Icons.Filled.Settings, null, tint = Color.White, modifier = Modifier.size(32.dp))
        }
        Spacer(Modifier.height(12.dp))
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Row(
                Modifier.weight(1f).height(52.dp)
                    .background(Color.Black.copy(alpha = 0.25f), RoundedCornerShape(26.dp))
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Filled.Search, null, tint = Color.White)
                Spacer(Modifier.width(12.dp))
                Text("Search 500+ Route", color = Color.White.copy(alpha = 0.7f), fontSize = 18.sp)
            }
            Spacer(Modifier.width(12.dp))
            Icon(Icons.Filled.Notifications, null, tint = Color.White, modifier = Modifier.size(34.dp))
        }
        Spacer(Modifier.height(8.dp))
    }
}

@Composable
private fun StopChip(label: String, selected: Boolean, onClick: () -> Unit) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(24.dp),
        color = if (selected) OneColors.Red else OneColors.ChipGrey.copy(alpha = 0.6f)
    ) {
        Text(label, color = Color.White, fontSize = 20.sp,
            modifier = Modifier.padding(horizontal = 30.dp, vertical = 8.dp))
    }
}

@Composable
private fun StopRow(stop: BusStop) {
    Column {
        Row(Modifier.fillMaxWidth().padding(vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically) {
            Column(Modifier.weight(1f)) {
                Text(stop.name, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text("towards ${stop.towards}", fontSize = 18.sp, color = OneColors.Grey)
            }
            OutlinedButton(
                onClick = { /* TODO: open buses of this stop */ },
                shape = RoundedCornerShape(24.dp),
                border = BorderStroke(1.dp, OneColors.Green)
            ) { Text("View Buses", color = OneColors.Green, fontSize = 18.sp) }
        }
        HorizontalDivider(color = Color(0xFFE6E6E6))
    }
}
