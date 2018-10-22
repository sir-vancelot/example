package com.batman.example.presentation.misc

import java.text.SimpleDateFormat
import java.util.*

object BatmanTimeStamper {
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
