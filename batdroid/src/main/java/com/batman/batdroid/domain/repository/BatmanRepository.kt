package com.batman.batdroid.domain.repository

import com.batman.batdroid.domain.datastore.BatmanDBDatastore
import com.batman.batdroid.domain.model.BatmanModel

/**
 * A [BatmanRepository] is used to link a parent [BatmanDBDatastore] to its children [BatmanDBDatastores][BatmanDBDatastore]
 * that reference it via a [database][android.database.sqlite.SQLiteDatabase] foreign key.
 */
abstract class BatmanRepository<ParentModel : BatmanModel, ParentDBDatastore : BatmanDBDatastore<ParentModel>>(protected val parentDBDatastore: ParentDBDatastore) {
    /**
     * Used to CREATE a database entry for a [ParentModel] and its [ChildModels][BatmanModel].
     */
    abstract fun create(parentModel: ParentModel)

    /**
     * Used to READ a database entry for a [ParentModel] and its [ChildModels][BatmanModel].
     */
    fun read(parentId: String): ParentModel {
        val parent = parentDBDatastore.readAllAsMap()[parentId]!!
        populateChildren(parent)
        return parent
    }

    /**
     * Used to READ all database entries for [ParentModels][ParentModel] as a [List].
     */
    fun readAllAsList(): List<ParentModel> {
        val parentModels = parentDBDatastore.readAllAsList()
        for (parentModel in parentModels) {
            populateChildren(parentModel)
        }
        return parentModels
    }

    /**
     * Used to READ all database entries for [ParentModels][ParentModel] as a [Map].
     */
    fun readAllAsMap(): HashMap<String, ParentModel> {
        val parentModels = parentDBDatastore.readAllAsMap()
        for ((_, parentModel) in parentModels) {
            populateChildren(parentModel)
        }
        return parentModels
    }

    /**
     * Used to READ all database entries for [ParentModels][ParentModel] that reference an
     * [ownerId][com.batman.batdroid.domain.datastore.db.query.BatmanDBQuery.ownerIds] as a [List].
     */
    fun readListByOwnerId(rowId: String, colIdKey: String): List<ParentModel> {
        val parentModels = parentDBDatastore.readListByOwnerId(rowId, colIdKey)
        for (parentModel in parentModels) {
            populateChildren(parentModel)
        }
        return parentModels
    }

    /**
     * Used to READ all database entries for [ParentModels][ParentModel] that reference an
     * [ownerId][com.batman.batdroid.domain.datastore.db.query.BatmanDBQuery.ownerIds] as a [Map].
     */
    fun readMapByOwnerId(rowId: String, colIdKey: String): HashMap<String, ParentModel> {
        val parentModels = parentDBDatastore.readMapByOwnerId(rowId, colIdKey)
        for ((_, parentModel) in parentModels) {
            populateChildren(parentModel)
        }
        return parentModels
    }

    /**
     * Used to UPDATE the database entries for a [ParentModel].
     */
    abstract fun update(parentModel: ParentModel)

    /**
     * Used to DELETE the database entries for a [ParentModel].
     */
    fun delete(parentModel: ParentModel) {
        parentDBDatastore.delete(parentModel)
    }

    /**
     * Used to populate the child models for a [ParentModel] in a [read], [readListByOwnerId], or [readMapByOwnerId]
     */
    abstract fun populateChildren(parentModel: ParentModel)
}