package com.batman.example.domain.datastore.db

import android.database.Cursor

import java.util.Date

object DBHelper {
    // <editor-fold desc="Helper functions to get objects out of database cursors">
    fun booleanToLong(value: Boolean): Long {
        return (if (value) 1 else 0).toLong()
    }

    fun getString(cursor: Cursor, columnName: String): String {
        return cursor.getString(cursor.getColumnIndex(columnName))
    }

    fun getBoolean(cursor: Cursor, columnName: String): Boolean {
        return cursor.getLong(cursor.getColumnIndex(columnName)) != 0L
    }

    fun getLong(cursor: Cursor, columnName: String): Long? {
        return if (cursor.isNull(cursor.getColumnIndex(columnName))) null else cursor.getLong(cursor.getColumnIndex(columnName))

    }

    fun getFloat(cursor: Cursor, columnName: String): Float {
        return cursor.getFloat(cursor.getColumnIndex(columnName))
    }

    fun getInt(cursor: Cursor, columnName: String): Int? {
        return if (cursor.isNull(cursor.getColumnIndex(columnName))) null else cursor.getInt(cursor.getColumnIndex(columnName))

    }

    fun getShort(cursor: Cursor, columnName: String): Short? {
        return if (cursor.isNull(cursor.getColumnIndex(columnName))) null else cursor.getShort(cursor.getColumnIndex(columnName))

    }

    fun getDate(cursor: Cursor, columnName: String): Date? {
        if (cursor.isNull(cursor.getColumnIndex(columnName))) {
            return null
        }
        val totalMilliseconds = cursor.getLong(cursor.getColumnIndex(columnName))
        return Date(totalMilliseconds)
    }

    fun getBytes(cursor: Cursor, columnName: String): ByteArray {
        return cursor.getBlob(cursor.getColumnIndex(columnName))
    }
    // </editor-fold>
}
