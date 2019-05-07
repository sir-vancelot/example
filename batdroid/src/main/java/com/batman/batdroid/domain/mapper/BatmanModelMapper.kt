package com.batman.batdroid.domain.mapper

import android.database.Cursor
import com.batman.batdroid.domain.datastore.db.BatmanDBHelper
import com.batman.batdroid.domain.datastore.db.query.BatmanDBQuery
import com.batman.batdroid.domain.model.BatmanModel

/**
 * A [BatmanModelMapper] is responsible for transforming a [Cursor] (the return of a [database][android.database.sqlite.SQLiteDatabase]
 * query) into the corresponding [BatmanModel], [List]<[BatmanModel]>, or [Map]<[id][String], [BatmanModel]>.
 */
abstract class BatmanModelMapper<M : BatmanModel>(private val queryBatman: BatmanDBQuery<M>) {
    /**
     * Used to transform a [Cursor] into a [List]<[BatmanModel]>.
     */
    fun transformList(cursor: Cursor): List<M> {
        val models = ArrayList<M>()
        while (cursor.moveToNext()) {
            models.add(transform(cursor))
        }
        return models
    }

    /**
     * Used to transform a [Cursor] into the [BatmanModel] the [Cursor] is currently pointing to.
     */
    abstract fun transform(cursor: Cursor): M

    /**
     * Used to transform a [Cursor] into a [Map]<[id][String], [BatmanModel]>.
     */
    fun transformMap(cursor: Cursor): HashMap<String, M> {
        val map = HashMap<String, M>()
        while (cursor.moveToNext()) {
            val index = BatmanDBHelper.getString(cursor, queryBatman.identityColumn)
            val normalizedId = index.toLowerCase()
            map[normalizedId] = transform(cursor)
        }
        return map
    }
}