package com.batman.batdroid.utils

import android.content.Intent

/**
 * Used to parse data passed to an activity.
 */
object BatmanIntentHelper {
    /**
     * Used to get a [String] from an [intent's][Intent] [extras][Intent.getExtras].
     */
    fun getStringExtraFromIntent(intent: Intent): String {
        return intent.getStringExtra(Intent.EXTRA_TEXT)
    }

    /**
     * Used to get a [String] from an [intent's][Intent] [dataString][Intent.getDataString]
     */
    fun getStringFromData(intent: Intent): String {
        return if (intent.dataString == null) "" else intent.dataString!!
    }

    /**
     * Used to get a [List] of [strings][String] from an [intent's][Intent] [clipData][Intent.getClipData].
     */
    fun getStringsFromClipData(intent: Intent): ArrayList<String> {
        val strings = ArrayList<String>()
        if (intent.clipData != null) {
            for (i in 0 until intent.clipData!!.itemCount) {
                strings.add(intent.clipData!!.getItemAt(i).uri.toString())
            }
        }
        return strings
    }
}