package com.batman.example.domain.datastore.db.query

import android.content.ContentValues
import com.batman.batdroid.domain.datastore.db.query.BatmanDBQuery
import com.batman.example.domain.model.ExampleStringModel

object ExampleStringQuery: BatmanDBQuery<ExampleStringModel>() {

    override val tableName = "example_string_table"
    override val identityColumn = COLUMNS.EXAMPLE_ID

    object COLUMNS {
        const val EXAMPLE_ID = "id"
        const val EXAMPLE_STRING = "example_string"
    }

    override val createTable = "CREATE TABLE " + tableName + " (" +
                COLUMNS.EXAMPLE_ID + " TEXT NOT NULL PRIMARY KEY, " +
                COLUMNS.EXAMPLE_STRING + " TEXT NOT NULL" +
                ");"

    override val selectAll = "SELECT * FROM $tableName;"

    override fun insert(normalizedId: String, model: ExampleStringModel): ContentValues {
        val values = ContentValues()
        values.put(COLUMNS.EXAMPLE_ID, normalizedId)
        return contentValueHelper(values, model)
    }

    override fun contentValueHelper(contentValues: ContentValues, model: ExampleStringModel): ContentValues {
        contentValues.put(COLUMNS.EXAMPLE_STRING, model.exampleString)
        return contentValues
    }
}