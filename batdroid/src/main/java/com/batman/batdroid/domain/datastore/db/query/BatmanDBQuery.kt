package com.batman.batdroid.domain.datastore.db.query

import android.content.ContentValues
import com.batman.batdroid.domain.model.BatmanModel

/**
 * The [BatmanDBQuery] is an abstraction for an object which will hold the information necessary for
 * a [BatmanDBDatastore][com.batman.batdroid.domain.datastore.BatmanDBDatastore] or
 * [BatmanEncryptedDBDatastore][com.batman.batdroid.domain.datastore.BatmanEncryptedDBDatastore]
 * to manage a database table.
 */
abstract class BatmanDBQuery<Model: BatmanModel> {

    /**
     * Requires a reference to the identity column of a table so that
     * [Mapper][com.batman.batdroid.domain.mapper.BatmanModelMapper] classes can map a
     * [Cursor][android.database.Cursor] to a [HashMap] of [BatmanModels][BatmanModel].
     */
    abstract val identityColumn: String

    /**
     * Used for identifying the foreign keys for a table by mapping the [BatmanModel]
     * classes to their corresponding columnIds.
     * Ex:
     * ```
     * override val ownerIds = mapOf(Pair(MyOtherModel::class.toString(), Column.MY_FOREIGN_KEY))
     * ```
     */
    open val ownerIds: Map<String, String>? = null

    /**
     * Requires a name for the database table.
     */
    abstract val tableName: String

    /**
     * Requires a sql definition for creating the database table.
     *
     * Ex:
     * ```
     * override val createTable = "CREATE TABLE " + tableName + " (" +
     *                             Column.ID + " STRING NOT NULL PRIMARY KEY, " +
     *                             Column.MY_STRING + " STRING NOT NULL, " +
     *                             Column.MY_FOREIGN_KEY + "STRING NOT NULL,
     *                             "FOREIGN KEY(" + Column.MY_FOREIGN_KEY + ")" +
     *                             "REFERENCES " + MyOtherDBQuery.tableName + "(" + MyOtherDBQuery.Column.ID + ")" +
     *                             ");"
     * ```
     */
    abstract val createTable: String

    /**
     * Requires a sql definition for selecting all rows from the database table.
     */
    abstract val selectAll: String

    /**
     * Requires a definition for creating the [ContentValues] for inserting the [BatmanModel] into
     * the database table. This function should insert the identity column and any foreign key values
     * into the [ContentValues] and then have the [contentValueHelper()][contentValueHelper] function
     * insert any other attributes.
     * Ex:
     * ```
     * override fun insert(normalizedId: String, model: MyModel): ContentValues {
     *    val values = ContentValues()
     *    values.put(Column.ID, normalizedId)
     *    values.put(Column.MY_FOREIGN_KEY, model.myForeignKey)
     *    return contentValueHelper(values, model)
     * }
     * ```
     */
    abstract fun insert(normalizedId: String, model: Model): ContentValues

    /**
     * Used for loading all updatable attributes into [ContentValues].
     */
    fun update(model: Model): ContentValues {
        val contentValues = ContentValues()
        return contentValueHelper(contentValues, model)
    }

    /**
     * Requires a definition for loading all attributes of a [BatmanModel] other that the identity
     * column and foreign keys into [ContentValues].
     * Ex:
     * ```
     * override fun contentValueHelper(contentValues: ContentValues, model: FinModel): ContentValues {
     *    contentValues.put(Column.MY_STRING, model.myString)
     *    return contentValues
     * }
     * ```
     */
    abstract fun contentValueHelper(contentValues: ContentValues, model: Model): ContentValues
}