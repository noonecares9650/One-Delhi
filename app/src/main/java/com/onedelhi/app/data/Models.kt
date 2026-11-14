package com.onedelhi.app.data

data class BusStop(val name: String, val towards: String)

data class BusTicket(
    val route: String,
    val unitPrice: String,
    val dateTime: String,
    val passengers: Int,
    val total: String,
    val boardingStop: String,
    val ticketId: String,
    val isValid: Boolean
)

// TODO: replace with real API / DB data
object MockData {
    val stops = listOf(
        BusStop("Mehrauli Terminal", "Qutub Minar"),
        BusStop("Mehrauli Terminal", "Qutub Minar"),
        BusStop("Kishangarh Crossing", "B3 Vasant Kunj Ken..."),
        BusStop("Qutub Minar", "T B Hospital"),
        BusStop("Kishan Garh", "Vasant Kunj SecD/34"),
        BusStop("Kishangarh Crossing", "ICSSR"),
    )

    val tickets = listOf(
        BusTicket(
            route = "502", unitPrice = "₹10.0",
            dateTime = "05-11-2026 17:27:56", passengers = 2, total = "₹18.0",
            boardingStop = "Mehrauli Terminal",
            ticketId = "T0511202637d9615163", isValid = false
        )
    )
}
