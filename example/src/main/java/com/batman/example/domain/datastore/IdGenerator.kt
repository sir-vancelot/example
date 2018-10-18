package com.batman.example.domain.datastore

import java.util.*

object IdGenerator {
    fun generateId(): String {
        return UUID.randomUUID().toString()
    }
}