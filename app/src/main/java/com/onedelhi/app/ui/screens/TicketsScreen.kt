package com.onedelhi.app.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ConfirmationNumber
import androidx.compose.material.icons.filled.Train
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.onedelhi.app.data.BusTicket
import com.onedelhi.app.data.MockData
import com.onedelhi.app.ui.theme.OneColors

@Composable
fun TicketsScreen() {
    Column(Modifier.fillMaxSize().background(OneColors.ScreenBg).verticalScroll(rememberScrollState())) {
        // Illustration header (TODO: replace with the real illustration drawable)
        Box(Modifier.fillMaxWidth().height(170.dp)
            .background(Brush.verticalGradient(listOf(OneColors.ScreenBg, OneColors.HeaderBlue)))) {
            Box(Modifier.align(Alignment.TopCenter).padding(top = 60.dp).fillMaxWidth()
                .height(3.dp).background(OneColors.Orange))
        }
        Row(Modifier.fillMaxWidth().padding(horizontal = 16.dp).offset(y = (-24).dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            ActionCard("Bus\nTicket", Color(0xFFFFD54F), badge = null, modifier = Modifier.weight(1f))
            ActionCard("Bus\nPasses", Color(0xFFD64FC0), badge = "New", modifier = Modifier.weight(1f))
            ActionCard("Metro\nTicket", OneColors.Red, badge = null, modifier = Modifier.weight(1f),
                bg = Color(0xFFDDDDDD), metro = true)
        }

        SectionTitle("My Bus Ticket")
        MockData.tickets.forEach { TicketCard(it) }

        SectionTitle("My Metro Ticket")
        EmptyCard("No Ticket Available")

        SectionTitle("My Bus Pass", "View all passes")
        EmptyCard("Click to View")
        Spacer(Modifier.height(16.dp))
    }
}

@Composable
private fun ActionCard(
    label: String, tint: Color, badge: String?, modifier: Modifier,
    bg: Color = Color.White, metro: Boolean = false
) {
    Box(modifier) {
        Card(Modifier.fillMaxWidth().height(96.dp), shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = bg),
            elevation = CardDefaults.cardElevation(4.dp)) {
            Box(Modifier.fillMaxSize().padding(14.dp)) {
                Text(label, fontSize = 20.sp, fontWeight = FontWeight.Medium,
                    modifier = Modifier.align(Alignment.BottomStart), lineHeight = 24.sp)
                Icon(if (metro) Icons.Filled.Train else Icons.Filled.ConfirmationNumber, null,
                    tint = tint, modifier = Modifier.align(Alignment.TopEnd).size(32.dp))
            }
        }
        if (badge != null) {
            Text(badge, color = Color.White, fontSize = 14.sp,
                modifier = Modifier.align(Alignment.TopCenter).offset(y = (-10).dp)
                    .background(Color(0xFFF08A8A), RoundedCornerShape(8.dp))
                    .padding(horizontal = 10.dp, vertical = 3.dp))
        }
    }
}

@Composable
private fun SectionTitle(title: String, action: String = "View all tickets") {
    Row(Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp, top = 24.dp, bottom = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Text(title, fontSize = 24.sp, fontWeight = FontWeight.Medium)
        Text(action, fontSize = 18.sp, color = OneColors.Grey)
    }
}

@Composable
private fun EmptyCard(text: String) {
    Card(Modifier.fillMaxWidth().padding(horizontal = 16.dp).height(74.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(3.dp)) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text, fontSize = 24.sp)
        }
    }
}

@Composable
private fun TicketCard(t: BusTicket) {
    Card(Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(3.dp)) {
        Column {
            Box(Modifier.fillMaxWidth().height(14.dp).background(Color(0xFF808080)))
            Box {
                Column(Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 14.dp)) {
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(t.route, fontSize = 30.sp)
                        Text(t.unitPrice, fontSize = 30.sp)
                    }
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(t.dateTime, fontSize = 30.sp)
                        Text("x ${t.passengers}", fontSize = 30.sp)
                    }
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Pts", fontSize = 30.sp)
                        Text(t.total, fontSize = 30.sp)
                    }
                    Text(t.boardingStop, fontSize = 30.sp)
                    Spacer(Modifier.height(10.dp))
                    Text(t.ticketId, fontSize = 22.sp, textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth())
                }
                if (!t.isValid) InvalidStamp(Modifier.align(Alignment.Center))
            }
        }
    }
}

@Composable
private fun InvalidStamp(modifier: Modifier = Modifier) {
    Box(
        modifier.rotate(-8f)
            .border(BorderStroke(3.dp, OneColors.StampRed.copy(alpha = 0.9f)))
            .padding(horizontal = 28.dp, vertical = 2.dp)
    ) {
        Text("INVALID", color = OneColors.StampRed.copy(alpha = 0.95f), fontSize = 76.sp,
            fontWeight = FontWeight.Black, maxLines = 1, softWrap = false)
    }
}
