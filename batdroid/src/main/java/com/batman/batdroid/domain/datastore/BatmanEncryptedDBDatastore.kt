package com.batman.batdroid.domain.datastore

import com.batman.batdroid.domain.datastore.db.BatmanEncryptedDBOpenHelper
import com.batman.batdroid.domain.datastore.db.query.BatmanDBQuery
import com.batman.batdroid.domain.mapper.BatmanModelMapper
import com.batman.batdroid.domain.model.BatmanModel
import net.sqlcipher.database.SQLiteDatabase

/**
 * The [BatmanEncryptedDBDatastore] is a class which implements the [C.R.U.D](https://en.wikipedia.org/wiki/Create,_read,_update_and_delete)
 * database model as well as some more specific read functions. A [BatmanEncryptedDBDatastore] requires a
 * [dbOpenHelper], [batmanQuery], and [mapper] for construction.
 */
open class BatmanEncryptedDBDatastore<Model : BatmanModel>(protected val dbOpenHelper: BatmanEncryptedDBOpenHelper,
                                                           protected val batmanQuery: BatmanDBQuery<Model>,
                                                           protected val mapper: BatmanModelMapper<Model>) {
    /**
     * Provides a cache in the form of a [map][HashMap] to store [models][Model] by their [String] id and
     * reduce the number of database accesses.
     */
    private var cacheMap: HashMap<String, Model> = HashMap()
    /**
     * Provides a cache in the form of a [list][ArrayList] to store [models][Model] by their [String] id and
     * reduce the number of database accesses.
     */
    private var cacheList: ArrayList<Model> = ArrayList()

    /**
     * Used to create a database entry for a [model].
     */
    open fun create(model: Model) {
        val normalizedId = model.id.unique.toLowerCase()

        val db = dbOpenHelper.writableDatabase
        db.insertOrThrow(batmanQuery.tableName, null, batmanQuery.insert(normalizedId, model))

        synchronized(cacheList as Any) {
            if (cacheList.size > 0) {
                cacheList.add(model)
            }
        }

        synchronized(cacheMap as Any) {
            if (cacheMap.size > 0) {
                cacheMap[normalizedId] = model
            }
        }
    }

    /**
     * Used to read a [Model] (or null if it does not exist) by the id of the [Model].
     */
    open fun read(id: String): Model? {
        val normalizedId = id.toLowerCase()
        return if (cacheMap.containsKey(normalizedId)) {
            cacheMap[normalizedId]
        } else {
            readAllAsMap()[normalizedId]
        }
    }

    /**
     * Used to read all of the [models][Model] from a table as a [List].
     */
    open fun readAllAsList(): List<Model> {
        synchronized(cacheList as Any) {
            return if (cacheList.size == 0) {
                val db = dbOpenHelper.readableDatabase
                val cursor = db.rawQuery(batmanQuery.selectAll, arrayOfNulls<String>(0))
                val models = mapper.transformList(cursor)
                cursor.close()

                cacheList.addAll(models)

                cacheList
            } else {
                cacheList
            }
        }
    }

    /**
     * Used to read all of the [models][Model] from a table as a [Map<id, model>][Map].
     */
    open fun readAllAsMap(): HashMap<String, Model> {
        synchronized(cacheMap as Any) {
            return if (cacheMap.size == 0) {
                val db = dbOpenHelper.readableDatabase
                val cursor = db.rawQuery(batmanQuery.selectAll, arrayOfNulls<String>(0))
                val models = mapper.transformMap(cursor)
                cursor.close()

                cacheMap.putAll(models)

                cacheMap
            } else {
                cacheMap
            }
        }
    }

    /**
     * Used to a [List] read all of the [models][Model] that reference the owner id ([ownerId])
     * corresponding to the table column derived from
     * [BatmanDBQuery.ownerIds][com.batman.batdroid.domain.datastore.db.query.BatmanDBQuery][[colIdKey]].
     */
    open fun readListByOwnerId(ownerId: String, colIdKey: String): List<Model> {
        val normalizedId = ownerId.toLowerCase()

        return if (batmanQuery.ownerIds != null) {
            val db = dbOpenHelper.readableDatabase
            val ownerIdKey = batmanQuery.ownerIds!![colIdKey]
            val cursor = db.query(batmanQuery.tableName, null, "$ownerIdKey=?", arrayOf(normalizedId), null, null, null)
            val models = mapper.transformList(cursor)
            cursor.close()

            models
        } else {
            ArrayList()
        }
    }

    /**
     * Used to a [Map] read all of the [models][Model] that reference the owner id ([ownerId])
     * corresponding to the table column derived from
     * [BatmanDBQuery.ownerIds][com.batman.batdroid.domain.datastore.db.query.BatmanDBQuery][[colIdKey]].
     */
    open fun readMapByOwnerId(rowId: String, colIdKey: String): HashMap<String, Model> {
        val normalizedId = rowId.toLowerCase()

        return if (batmanQuery.ownerIds != null) {
            val db = dbOpenHelper.readableDatabase
            val ownerIdKey = batmanQuery.ownerIds!![colIdKey]
            val cursor = db.query(batmanQuery.tableName, null, "$ownerIdKey=?", arrayOf(normalizedId), null, null, null)
            val models = mapper.transformMap(cursor)
            cursor.close()

            models
        } else {
            HashMap()
        }
    }

    /**
     * Used to update the database entry of a [model].
     */
    open fun update(model: Model) {
        val normalizedId = model.id.unique.toLowerCase()

        val db = dbOpenHelper.writableDatabase
        db.updateWithOnConflict(batmanQuery.tableName, batmanQuery.update(model), batmanQuery.identityColumn + "=?", arrayOf(normalizedId), SQLiteDatabase.CONFLICT_ABORT)
    }

    /**
     * Used to delete the database entry of a [model].
     */
    open fun delete(model: Model) {
        val normalizedId = model.id.unique.toLowerCase()

        val db = dbOpenHelper.writableDatabase
        db.delete(batmanQuery.tableName, batmanQuery.identityColumn + "=?", arrayOf(normalizedId))

        synchronized(cacheList as Any) {
            cacheList.remove(model)
        }

        synchronized(cacheMap as Any) {
            cacheMap.remove(normalizedId)
        }
    }
}