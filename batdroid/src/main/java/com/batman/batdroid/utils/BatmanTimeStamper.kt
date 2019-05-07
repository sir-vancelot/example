package com.batman.batdroid.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * Used to generate timestamps for logs and model creation.
 */
object BatmanTimeStamper {
    /**
     * Used to get a timestamp for the current time.
     */
    fun generateTimestamp(): String {
        val now = System.currentTimeMillis()
        return millisToTime(now)
    }

    /**
     * Used to format a time to the pattern "dd/MM/yyyy-HH:mm:ss".
     */
    private fun millisToTime(millis: Long): String {
        val cal = Calendar.getInstance()
        cal.timeInMillis = millis
        val dateFormat = SimpleDateFormat("dd/MM/yyyy-HH:mm:ss", Locale.ENGLISH)
        return dateFormat.format(cal.time)
    }
}
