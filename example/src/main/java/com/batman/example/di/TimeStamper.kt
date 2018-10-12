package com.batman.example.di

import java.text.SimpleDateFormat
import java.util.Calendar

/**
 * Created on 4/26/2016.
 */
object TimeStamper {

    fun generateTimestamp(): String {
        val now = System.currentTimeMillis()
        return millisToTime(now)
    }

    private fun millisToTime(millis: Long): String {
        val cal = Calendar.getInstance()
        cal.timeInMillis = millis
        val dateFormat = SimpleDateFormat("yyyyMMdd_HHmmss")
        return dateFormat.format(cal.time)
    }
}
