package com.batman.example.domain.db.datastore

import android.database.sqlite.SQLiteDatabase
import com.batman.example.domain.db.ExampleDBOpenHelper
import com.batman.example.domain.db.mapper.Example1ModelMapper
import com.batman.example.domain.db.query.Example1Query
import com.batman.example.domain.model.Example1Model
import java.util.*

class Example1Datastore(private val exampleDBOpenHelper: ExampleDBOpenHelper) {

    fun create(): Example1Model {
        val example1Model = Example1Model(nextId())
        val db = exampleDBOpenHelper.writableDatabase
        db.insertOrThrow(Example1Query.TABLE_NAME, null, Example1Query.INSERT(example1Model))
        return example1Model
    }

    fun read(): List<Example1Model> {
        val db = exampleDBOpenHelper.readableDatabase
        val cursor = db.rawQuery(Example1Query.SELECT_ALL, arrayOfNulls<String>(0))

        val example1Models = Example1ModelMapper.transformList(cursor)

        cursor.close()

        return example1Models
    }

    fun update(example1Model: Example1Model) {
        val db = exampleDBOpenHelper.writableDatabase
        db.updateWithOnConflict(Example1Query.TABLE_NAME, Example1Query.UPDATE(example1Model), Example1Query.Column.EXAMPLE_ID + "=?", arrayOf(example1Model.exampleId), SQLiteDatabase.CONFLICT_ABORT)
    }

    fun delete(example1Model: Example1Model) {
        val db = exampleDBOpenHelper.writableDatabase
        db.delete(Example1Query.TABLE_NAME, Example1Query.Column.EXAMPLE_ID + "=?", arrayOf(example1Model.exampleId))
    }

    private fun nextId(): String {
        return UUID.randomUUID().toString()
    }
}