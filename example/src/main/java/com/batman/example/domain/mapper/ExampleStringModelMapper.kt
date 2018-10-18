package com.batman.example.domain.mapper

import android.database.Cursor
import com.batman.example.domain.db.DBHelper
import com.batman.example.domain.db.query.ExampleStringQuery.COLUMNS
import com.batman.example.infrastructure.model.ExampleStringModel

object ExampleStringModelMapper {
    // <editor-fold desc="Transform all of the entries in a cursor to their corresponding model">
    fun transformList(cursor: Cursor): ArrayList<ExampleStringModel> {
        val exampleStrings = ArrayList<ExampleStringModel>()
        while(cursor.moveToNext()) {
            exampleStrings.add(transform(cursor))
        }
        return exampleStrings
    }
    // </editor-fold>

    // <editor-fold desc="Transform the entry currently pointed to its corresponding model">
    private fun transform(cursor: Cursor): ExampleStringModel {
        val exampleId = DBHelper.getString(cursor, COLUMNS.EXAMPLE_ID)
        val exampleString = DBHelper.getString(cursor, COLUMNS.EXAMPLE_STRING)

        return ExampleStringModel(exampleId, exampleString)
    }
    // </editor-fold>
}