package com.batman.batdroid.domain.datastore.db

import android.content.Context
import com.batman.batdroid.utils.sha512
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SQLiteOpenHelper

/**
 * The [BatmanEncryptedDBOpenHelper] is an extension of [SQLiteOpenHelper]. It provides the
 * functions [getWritableDatabase()][getWritableDatabase] and [getReadableDatabase()][getReadableDatabase]
 * which can be used to abstract away obtaining writable and readable databases by providing a password.
 */
abstract class BatmanEncryptedDBOpenHelper(context: Context, DB_NAME: String, VERSION: Int): SQLiteOpenHelper(context, DB_NAME, null, VERSION) {
    /**
     * Provide a password for a database.
     */
    private var password: String = " "

    /**
     * Used for setting the private attribute password. This should only be done after
     * setting the password for the database. Which can done like the following in any class that
     * has an instantiated [BatmanEncryptedDBOpenHelper]...
     * ```
     * myFunction(password: String) {
     *    val database = dbOpenHelper.writableDatabase
     *    database.changePassword(password)
     *    dbOpenHelper.password = password
     * }
     * ```
     */
    fun setPassword(password: String) {
        this.password = password
    }

    /**
     * Used for obtaining a writable database without explicitly providing the password.
     * The password must first be set for this to work properly. If this works properly without updating
     * the password, then the database is encrypted with the default password.
     */
    val writableDatabase: SQLiteDatabase
        get() = getWritableDatabase(password)

    /**
     * Used for obtaining a readable database without explicitly providing the password.
     * The password must first be set for this to work properly. If this works properly without updating
     * the password, then the database is encrypted with the default password.
     */
    val readableDatabase: SQLiteDatabase
        get() = getReadableDatabase(password)

}