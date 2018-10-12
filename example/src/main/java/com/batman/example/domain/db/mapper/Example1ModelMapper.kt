package com.batman.example.domain.db.mapper

import android.database.Cursor
import com.batman.example.domain.db.DBHelper
import com.batman.example.domain.db.query.Example1Query.Column
import com.batman.example.domain.model.Example1Model

object Example1ModelMapper {
    fun transformList(cursor: Cursor): List<Example1Model> {
        val example1Models = ArrayList<Example1Model>()
        while(cursor.moveToNext()) {
            example1Models.add(transform(cursor))
        }
        return example1Models
    }

    fun transform(cursor: Cursor): Example1Model {
        val exampleId = DBHelper.getString(cursor, Column.EXAMPLE_ID)
        val exampleString = DBHelper.getString(cursor, Column.EXAMPLE_STRING)
        val exampleInt = DBHelper.getInt(cursor, Column.EXAMPLE_INT)

        return Example1Model(exampleId, exampleString, exampleInt!!)
    }
}