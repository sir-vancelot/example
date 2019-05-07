package com.batman.batdroid.utils

import java.util.*

/**
 * Used to generate UUID's for use in uniquely identifying models.
 */
object BatmanIdGenerator {
    /**
     * Used to generate UUID's for use in uniquely identifying models.
     */
    fun generateId(): String {
        return UUID.randomUUID().toString()
    }
}