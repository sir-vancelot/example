package com.batman.example.domain.db.query

import android.content.ContentValues
import com.batman.example.infrastructure.model.ExampleStringModel

object ExampleStringQuery: Query<ExampleStringModel>() {
    /*
       Declare a name for the database table
    */
    override val tableName = "example_string_table"

    // <editor-fold desc="Columns headers for this table">
    object COLUMNS {
        const val EXAMPLE_ID = "id"
        const val EXAMPLE_STRING = "example_string"
    }
    // </editor-fold>

    // <editor-fold desc="Return a string to be used for the table's creation">
    override fun createTable(): String {
        return "CREATE TABLE " + tableName + " (" +
                COLUMNS.EXAMPLE_ID + " TEXT NOT NULL PRIMARY KEY, " +
                COLUMNS.EXAMPLE_STRING + " TEXT NOT NULL" +
                ");"
    }
    // </editor-fold>

    // <editor-fold desc="Return a string to be used to select all of the rows from this table">
    override fun selectAll(): String {
        return  "SELECT * FROM $tableName;"
    }
    // </editor-fold>

    // <editor-fold desc="Create ContentValues to insert the provided model">
    override fun insert(model: ExampleStringModel): ContentValues {
        val values = ContentValues()
        values.put(COLUMNS.EXAMPLE_ID, model.exampleId)
        return contentValueHelper(values, model)
    }
    // </editor-fold>

    // <editor-fold desc="Create ContentValues to update the provided model">
    override fun update(model: ExampleStringModel): ContentValues {
        val values = ContentValues()
        return contentValueHelper(values, model)
    }
    // </editor-fold>

    // <editor-fold desc="Setup ContentValues to prevent SQL injection">
    private fun contentValueHelper(values: ContentValues, exampleString: ExampleStringModel): ContentValues {
        values.put(COLUMNS.EXAMPLE_STRING, exampleString.exampleString)
        return values
    }
    // </editor-fold>
}