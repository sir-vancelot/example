package com.batman.batdroid.domain.datastore.db

import android.database.Cursor

import java.util.Date

/**
 * The [BatmanDBHelper] is a collection of helper functions for obtaining objects from a [Cursor].
 */
object BatmanDBHelper {
    /**
     * Used for getting a [String] from a table row within a [Cursor] by its [columnName]
     * or "" if the value is null.
     */
    fun getString(cursor: Cursor, columnName: String): String {
        return if(cursor.isNull(cursor.getColumnIndex(columnName))) "" else cursor.getString(cursor.getColumnIndex(columnName))
    }

    /**
     * Used for getting a [String] from a table row within a [Cursor] by its [columnName]
     * or null if the value is null.
     */
    fun getStringNullable(cursor: Cursor, columnName: String): String? {
        return if(cursor.isNull(cursor.getColumnIndex(columnName))) null else getString(cursor, columnName)
    }

    /**
     * Used for getting a [Boolean] from a table row within a [Cursor] by its [columnName]
     * or false if the value is null.
     */
    fun getBoolean(cursor: Cursor, columnName: String): Boolean {
        return if(cursor.isNull(cursor.getColumnIndex(columnName))) false else cursor.getInt(cursor.getColumnIndex(columnName)) != 0
    }

    /**
     * Used for getting a [Boolean] from a table row within a [Cursor] by its [columnName]
     * or null if the value is null.
     */
    fun getBooleanNullable(cursor: Cursor, columnName: String): Boolean? {
        return if(cursor.isNull(cursor.getColumnIndex(columnName))) null else cursor.getLong(cursor.getColumnIndex(columnName)) != 0L
    }

    /**
     * Used for getting a [Long] from a table row within a [Cursor] by its [columnName]
     * or -1L if the value is null.
     */
    fun getLong(cursor: Cursor, columnName: String): Long {
        return if (cursor.isNull(cursor.getColumnIndex(columnName))) -1L else cursor.getLong(cursor.getColumnIndex(columnName))
    }

    /**
     * Used for getting a [Long] from a table row within a [Cursor] by its [columnName]
     * or null if the value is null.
     */
    fun getLongNullable(cursor: Cursor, columnName: String): Long? {
        return if (cursor.isNull(cursor.getColumnIndex(columnName))) null else cursor.getLong(cursor.getColumnIndex(columnName))
    }

    /**
     * Used for getting a [Float] from a table row within a [Cursor] by its [columnName]
     * or -1f if the value is null.
     */
    fun getFloat(cursor: Cursor, columnName: String): Float {
        return if (cursor.isNull(cursor.getColumnIndex(columnName))) -1f else cursor.getFloat(cursor.getColumnIndex(columnName))
    }

    /**
     * Used for getting a [Float] from a table row within a [Cursor] by its [columnName]
     * or null if the value is null.
     */
    fun getFloatNullable(cursor: Cursor, columnName: String): Float? {
        return if (cursor.isNull(cursor.getColumnIndex(columnName))) null else cursor.getFloat(cursor.getColumnIndex(columnName))
    }

    /**
     * Used for getting a [Int] from a table row within a [Cursor] by its [columnName]
     * or -1 if the value is null.
     */
    fun getInt(cursor: Cursor, columnName: String): Int {
        return if (cursor.isNull(cursor.getColumnIndex(columnName))) -1 else cursor.getInt(cursor.getColumnIndex(columnName))
    }

    /**
     * Used for getting a [Int] from a table row within a [Cursor] by its [columnName]
     * or null if the value is null.
     */
    fun getIntNullable(cursor: Cursor, columnName: String): Int? {
        return if (cursor.isNull(cursor.getColumnIndex(columnName))) null else cursor.getInt(cursor.getColumnIndex(columnName))
    }

    /**
     * Used for getting a [Double] from a table row within a [Cursor] by its [columnName]
     * or -1.0 if the value is null.
     */
    fun getDouble(cursor: Cursor, columnName: String): Double {
        return if (cursor.isNull(cursor.getColumnIndex(columnName))) -1.0 else cursor.getDouble(cursor.getColumnIndex(columnName))
    }

    /**
     * Used for getting a [Double] from a table row within a [Cursor] by its [columnName]
     * or null if the value is null.
     */
    fun getDoubleNullable(cursor: Cursor, columnName: String): Double? {
        return if (cursor.isNull(cursor.getColumnIndex(columnName))) null else getDouble(cursor, columnName)
    }

    /**
     * Used for getting a [Short] from a table row within a [Cursor] by its [columnName]
     * or -1 if the value is null.
     */
    fun getShort(cursor: Cursor, columnName: String): Short {
        return if (cursor.isNull(cursor.getColumnIndex(columnName))) -1 else cursor.getShort(cursor.getColumnIndex(columnName))
    }

    /**
     * Used for getting a [Short] from a table row within a [Cursor] by its [columnName]
     * or null if the value is null.
     */
    fun getShortNullable(cursor: Cursor, columnName: String): Short? {
        return if (cursor.isNull(cursor.getColumnIndex(columnName))) null else cursor.getShort(cursor.getColumnIndex(columnName))
    }

    /**
     * Used for getting a [Date] from a table row within a [Cursor] by its [columnName]
     * or null if the value is null.
     */
    fun getDate(cursor: Cursor, columnName: String): Date? {
        if (cursor.isNull(cursor.getColumnIndex(columnName))) {
            return null
        }
        val totalMilliseconds = cursor.getLong(cursor.getColumnIndex(columnName))
        return Date(totalMilliseconds)
    }

    /**
     * Used for getting a [Byte] from a table row within a [Cursor] by its [columnName].
     */
    fun getByte(cursor: Cursor, columnName: String) : Byte {
        return if (cursor.isNull(cursor.getColumnIndex(columnName))) 0.toByte() else cursor.getShort(cursor.getColumnIndex(columnName)).toByte()
    }

    /**
     * Used for getting a [Byte] from a table row within a [Cursor] by its [columnName]
     * or null if the value is null.
     */
    fun getByteNullable(cursor: Cursor, columnName: String): Byte? {
        return if (cursor.isNull(cursor.getColumnIndex(columnName))) null else cursor.getShort(cursor.getColumnIndex(columnName)).toByte()
    }

    /**
     * Used for getting a [ByteArray] from a table row within a [Cursor] by its [columnName].
     */
    fun getBytes(cursor: Cursor, columnName: String): ByteArray {
        return cursor.getBlob(cursor.getColumnIndex(columnName))
    }

    /**
     * Used for getting a [ByteArray] from a table row within a [Cursor] by its [columnName]
     * or null if the value is null.
     */
    fun getBytesNullable(cursor: Cursor, columnName: String): ByteArray? {
        return if (cursor.isNull(cursor.getColumnIndex(columnName))) null else cursor.getBlob(cursor.getColumnIndex(columnName))
    }
}
