package com.batman.example.domain.mapper

import android.database.Cursor
import com.batman.batdroid.domain.datastore.db.BatmanDBHelper
import com.batman.batdroid.domain.mapper.BatmanModelMapper
import com.batman.example.domain.datastore.db.query.ExampleStringQuery
import com.batman.example.domain.datastore.db.query.ExampleStringQuery.COLUMNS
import com.batman.example.domain.model.ExampleStringModel

object ExampleStringModelMapper: BatmanModelMapper<ExampleStringModel>(ExampleStringQuery) {
    override fun transform(cursor: Cursor): ExampleStringModel {
        val exampleId = BatmanDBHelper.getString(cursor, COLUMNS.EXAMPLE_ID)
        val exampleString = BatmanDBHelper.getString(cursor, COLUMNS.EXAMPLE_STRING)

        return ExampleStringModel(exampleId, exampleString)
    }
}