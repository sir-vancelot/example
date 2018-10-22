package com.batman.example.infrastructure.db.query

import android.content.ContentValues
import com.batman.example.domain.model.ExampleStringModel

object ExampleStringQuery: Query<ExampleStringModel> {

    override val tableName = "example_string_table"

    object COLUMNS {
        const val EXAMPLE_ID = "id"
        const val EXAMPLE_STRING = "example_string"
    }

    override val createTable = "CREATE TABLE " + tableName + " (" +
                COLUMNS.EXAMPLE_ID + " TEXT NOT NULL PRIMARY KEY, " +
                COLUMNS.EXAMPLE_STRING + " TEXT NOT NULL" +
                ");"

    override val selectAll = "SELECT * FROM $tableName;"

    override fun insert(model: ExampleStringModel): ContentValues {
        val values = ContentValues()
        values.put(COLUMNS.EXAMPLE_ID, model.exampleId)
        return contentValueHelper(values, model)
    }

    override fun update(model: ExampleStringModel): ContentValues {
        val values = ContentValues()
        return contentValueHelper(values, model)
    }

    override fun contentValueHelper(contentValues: ContentValues, model: ExampleStringModel): ContentValues {
        contentValues.put(COLUMNS.EXAMPLE_STRING, model.exampleString)
        return contentValues
    }
}