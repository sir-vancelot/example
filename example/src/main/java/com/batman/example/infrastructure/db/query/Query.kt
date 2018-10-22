package com.batman.example.infrastructure.db.query

import android.content.ContentValues

abstract class Query<Model> {
    abstract val tableName: String

    abstract fun createTable(): String
    abstract fun selectAll(): String
    abstract fun insert(model: Model): ContentValues
    abstract fun update(model: Model): ContentValues
}