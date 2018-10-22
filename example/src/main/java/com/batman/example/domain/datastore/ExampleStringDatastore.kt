package com.batman.example.domain.datastore

import android.database.sqlite.SQLiteDatabase
import com.batman.example.infrastructure.db.ExampleDBOpenHelper
import com.batman.example.infrastructure.mapper.ExampleStringModelMapper
import com.batman.example.infrastructure.db.query.ExampleStringQuery
import com.batman.example.domain.model.ExampleStringModel
import com.batman.example.infrastructure.datastore.Datastore

class ExampleStringDatastore(private val exampleDBOpenHelper: ExampleDBOpenHelper): Datastore<ExampleStringModel> {

    /*
       Allow other classes to access a cache instead of reading via database reads
    */
    val cache: ArrayList<ExampleStringModel>

    // <editor-fold desc="Execute in response to this classes creation">
    init {
        cache = read()

        if (cache.isEmpty()) { // For this example app, there will only be one database entry. Create it if it does not exist.
            val exampleStringModel = create()
            cache.add(exampleStringModel)
        }
    }
    // </editor-fold>

    // <editor-fold desc="Create a row in the database and return the model">
    override fun create(): ExampleStringModel {
        val exampleString = ExampleStringModel(IdGenerator.generateId())
        val db = exampleDBOpenHelper.writableDatabase
        db.insertOrThrow(ExampleStringQuery.tableName, null, ExampleStringQuery.insert(exampleString))
        return exampleString
    }
    // </editor-fold>

    // <editor-fold desc="Read all of the rows from the database and return the models"
    override fun read(): ArrayList<ExampleStringModel> {
        val db = exampleDBOpenHelper.readableDatabase
        val cursor = db.rawQuery(ExampleStringQuery.selectAll, arrayOfNulls<String>(0))

        val exampleStringModels = ExampleStringModelMapper.transformList(cursor)

        cursor.close()

        return exampleStringModels
    }
    // </editor-fold>

    // <editor-fold desc="Update the database row corresponding to the provided model">
    override fun update(model: ExampleStringModel) {
        val db = exampleDBOpenHelper.writableDatabase
        db.updateWithOnConflict(ExampleStringQuery.tableName, ExampleStringQuery.update(model), ExampleStringQuery.COLUMNS.EXAMPLE_ID + "=?", arrayOf(model.exampleId), SQLiteDatabase.CONFLICT_ABORT)
    }
    // </editor-fold>

    // <editor-fold desc="Delete the database row corresponding to the provided model">
    override fun delete(model: ExampleStringModel) {
        val db = exampleDBOpenHelper.writableDatabase
        db.delete(ExampleStringQuery.tableName, ExampleStringQuery.COLUMNS.EXAMPLE_ID + "=?", arrayOf(model.exampleId))
    }
    // </editor-fold>
}