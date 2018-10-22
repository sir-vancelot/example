package com.batman.example.infrastructure.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.batman.example.infrastructure.db.query.ExampleStringQuery

const val DB_NAME = "EXAMPLE.db"
const val VERSION = 1

class ExampleDBOpenHelper(context: Context): SQLiteOpenHelper(context, DB_NAME, null, VERSION) {
    // <editor-fold desc="Execute in response to the database file's creation">
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(ExampleStringQuery.createTable)
    }
    // </editor-fold>

    // <editor-fold desc="Execute in response to a change in the VERSION number">
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) { }
    // </editor-fold>
}