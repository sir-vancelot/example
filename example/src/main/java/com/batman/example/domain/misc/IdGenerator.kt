package com.batman.example.domain.misc

import java.util.*

object IdGenerator {
    fun generateId(): String {
        return UUID.randomUUID().toString()
    }
}