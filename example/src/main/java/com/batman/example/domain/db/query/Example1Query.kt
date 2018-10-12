package com.batman.example.domain.db.query

import android.content.ContentValues
import com.batman.example.domain.model.Example1Model

object Example1Query {
    val TABLE_NAME = "example1"

    object Column {
        val EXAMPLE_ID = "id"
        val EXAMPLE_STRING = "example_string"
        val EXAMPLE_INT = "example_int"
    }

    val CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
            Column.EXAMPLE_ID + " TEXT NOT NULL PRIMARY KEY, " +
            Column.EXAMPLE_STRING + " TEXT NOT NULL," +
            Column.EXAMPLE_INT + " INTEGER NOT NULL" +
            ");"

    fun INSERT(example1Model: Example1Model): ContentValues {
        val values = ContentValues()
        values.put(Column.EXAMPLE_ID, example1Model.exampleId)
        return contentValueHelper(values, example1Model)
    }

    fun UPDATE(example1Model: Example1Model): ContentValues {
        val values = ContentValues()
        return contentValueHelper(values, example1Model)
    }

    private fun contentValueHelper(values: ContentValues, example1Model: Example1Model): ContentValues {
        values.put(Column.EXAMPLE_STRING, example1Model.exampleString)
        values.put(Column.EXAMPLE_INT, example1Model.exampleInt)
        return values
    }

    val SELECT_ALL = "SELECT * FROM $TABLE_NAME;"
}