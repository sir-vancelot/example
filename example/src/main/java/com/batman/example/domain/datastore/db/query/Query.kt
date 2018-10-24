package com.batman.example.domain.datastore.db.query

import android.content.ContentValues

interface Query<Model> {
    /*
       Declare a name for the database table
    */
    val tableName: String

    /*
       Define the SQL to create the table
    */
    val createTable: String

    /*
       Define the SQL to select all entries in the table
    */
    val selectAll: String

    // <editor-fold desc="Create ContentValues to insert the provided model">
    fun insert(model: Model): ContentValues
    // </editor-fold>

    // <editor-fold desc="Create ContentValues to update the provided model">
    fun update(model: Model): ContentValues
    // </editor-fold>

    // <editor-fold desc="Setup ContentValues to prevent SQL injection">
    fun contentValueHelper(contentValues: ContentValues, model: Model): ContentValues
    // </editor-fold>
}