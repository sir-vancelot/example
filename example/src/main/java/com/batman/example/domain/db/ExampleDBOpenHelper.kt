package com.batman.example.domain.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.batman.example.domain.db.query.Example1Query

const val DB_NAME = "EXAMPLE.db"
const val VERSION = 1

class ExampleDBOpenHelper(context: Context): SQLiteOpenHelper(context, DB_NAME, null, VERSION) {
    // Call the create table functions here for a first time db setup
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(Example1Query.CREATE_TABLE)
    }

    // Changes to the DB after a release need to be implemented here to upgrade old databases
    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) { }

}