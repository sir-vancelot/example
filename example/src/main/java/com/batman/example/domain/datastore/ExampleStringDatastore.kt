package com.batman.example.domain.datastore

import android.database.sqlite.SQLiteDatabase
import com.batman.example.domain.misc.IdGenerator
import com.batman.example.domain.datastore.db.ExampleDBOpenHelper
import com.batman.example.domain.mapper.ExampleStringModelMapper
import com.batman.example.domain.datastore.db.query.ExampleStringQuery
import com.batman.example.domain.model.ExampleStringModel

class ExampleStringDatastore(private val exampleDBOpenHelper: ExampleDBOpenHelper): Datastore<ExampleStringModel> {
    override var cache = ArrayList<ExampleStringModel>()

    init {
        initialize()
    }

    override fun initialize() {
        cache = read()

        // Since this example app should only ever have one exampleString entry. If the read returns
        // empty, create the db entry (this will only happen on the first app launch).
        if (cache.isEmpty()) {
            create(ExampleStringModel(IdGenerator.generateId()))
        }
    }

    override fun create(model: ExampleStringModel) {
        val db = exampleDBOpenHelper.writableDatabase
        db.insertOrThrow(ExampleStringQuery.tableName, null, ExampleStringQuery.insert(model))
        cache.add(model)
    }

    override fun read(): ArrayList<ExampleStringModel> {
        val db = exampleDBOpenHelper.readableDatabase
        val cursor = db.rawQuery(ExampleStringQuery.selectAll, arrayOfNulls<String>(0))

        val exampleStringModels = ExampleStringModelMapper.transformList(cursor)

        cursor.close()

        return exampleStringModels
    }

    override fun update(model: ExampleStringModel) {
        val db = exampleDBOpenHelper.writableDatabase
        db.updateWithOnConflict(ExampleStringQuery.tableName, ExampleStringQuery.update(model), ExampleStringQuery.COLUMNS.EXAMPLE_ID + "=?", arrayOf(model.exampleId), SQLiteDatabase.CONFLICT_ABORT)
    }

    override fun delete(model: ExampleStringModel) {
        val db = exampleDBOpenHelper.writableDatabase
        db.delete(ExampleStringQuery.tableName, ExampleStringQuery.COLUMNS.EXAMPLE_ID + "=?", arrayOf(model.exampleId))
    }
}