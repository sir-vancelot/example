package com.batman.example.domain.mapper

import android.database.Cursor

interface ModelMapper<Model> {
    // <editor-fold desc="Transform all of the entries in a cursor to their corresponding model">
    fun transformList(cursor: Cursor): List<Model>
    // </editor-fold>

    // <editor-fold desc="Transform the entry currently pointed to its corresponding model">
    fun transform(cursor: Cursor): Model
    // </editor-fold>
}