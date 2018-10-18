package com.batman.example.presentation

import java.text.SimpleDateFormat
import java.util.*

object TimeStamper {
    fun generateTimestamp(): String {
        val now = System.currentTimeMillis()
        return millisToTime(now)
    }

    private fun millisToTime(millis: Long): String {
        val cal = Calendar.getInstance()
        cal.timeInMillis = millis
        val dateFormat = SimpleDateFormat("dd/MM/yyyy-HH:mm:ss", Locale.ENGLISH)
        return dateFormat.format(cal.time)
    }
}
