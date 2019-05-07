package com.batman.batdroid.domain.interactor.query.datastore

import com.batman.batdroid.domain.datastore.BatmanDBDatastore
import com.batman.batdroid.domain.interactor.query.BatmanReadAllByOwnerIdQuery
import com.batman.batdroid.domain.interactor.query.BatmanReadAllQuery
import com.batman.batdroid.domain.interactor.query.BatmanReadByIdQuery
import com.batman.batdroid.domain.model.BatmanModel
import io.reactivex.Maybe

/**
 * This [BatmanReadAllQueryHandler] is a [BatmanQueryHandler][com.batman.batdroid.domain.interactor.query.BatmanQueryHandler]
 * which is used to read all [BatmanModel's][BatmanModel] from a [BatmanDBDatastore].
 */
abstract class BatmanReadAllQueryHandler<Query : BatmanReadAllQuery, Model : BatmanModel>(protected val dbDatastore: BatmanDBDatastore<Model>) {
    /**
     * Defines what should occur by default when a [BatmanReadAllQuery] is queried to a [Map]
     */
    open fun queryMap(query: Query): Maybe<HashMap<String, Model>> {
        return Maybe.fromCallable {
            return@fromCallable dbDatastore.readAllAsMap()
        }
    }

    /**
     * Defines what should occur by default when a [BatmanReadAllQuery] is queried to a [List]
     */
    open fun queryList(query: Query): Maybe<List<Model>> {
        return Maybe.fromCallable {
            return@fromCallable dbDatastore.readAllAsList()
        }
    }
}

/**
 * This [BatmanReadAllByOwnerIdQueryHandler] is a [BatmanQueryHandler][com.batman.batdroid.domain.interactor.query.BatmanQueryHandler]
 * which is used to read all [BatmanModel's][BatmanModel] by their [ownerId][com.batman.batdroid.domain.datastore.db.query.BatmanDBQuery.ownerIds]
 * from a [BatmanDBDatastore].
 */
abstract class BatmanReadAllByOwnerIdQueryHandler<Query : BatmanReadAllByOwnerIdQuery, Model : BatmanModel>(protected val dbDatastore: BatmanDBDatastore<Model>) {
    /**
     * Defines what should occur by default when a [BatmanReadAllByOwnerIdQuery] is queried to a [Map]
     */
    open fun queryMap(query: Query): Maybe<HashMap<String, Model>> {
        return Maybe.create {
            val models = dbDatastore.readMapByOwnerId(query.ownerId, query.ownerIdKey)
            it.onSuccess(models)
        }
    }

    /**
     * Defines what should occur by default when a [BatmanReadAllByOwnerIdQuery] is queried to a [List]
     */
    open fun queryList(query: Query): Maybe<List<Model>> {
        return Maybe.create {
            val models = dbDatastore.readListByOwnerId(query.ownerId, query.ownerIdKey)
            it.onSuccess(models)
        }
    }
}

/**
 * This [BatmanReadAllByOwnerIdQueryHandler] is a [BatmanQueryHandler][com.batman.batdroid.domain.interactor.query.BatmanQueryHandler]
 * which is used to read a [BatmanModel] by its [identityColumn][com.batman.batdroid.domain.datastore.db.query.BatmanDBQuery.identityColumn]
 * from a [BatmanDBDatastore].
 */
abstract class BatmanReadByIdQueryHandler<Query : BatmanReadByIdQuery, Model : BatmanModel>(protected val dbDatastore: BatmanDBDatastore<Model>) {
    /**
     * Defines what should occur by default when a [BatmanReadByIdQuery] is queried to a [Map]
     */
    open fun queryMap(query: Query): Maybe<Model> {
        return Maybe.fromCallable {
            if (query.idString == null) return@fromCallable null
            else return@fromCallable dbDatastore.readAllAsMap()[query.idString]
        }
    }

    /**
     * Defines what should occur by default when a [BatmanReadByIdQuery] is queried to a [List]
     */
    open fun queryList(query: Query): Maybe<Model> {
        return Maybe.fromCallable {
            if (query.idInt == null) return@fromCallable null
            else return@fromCallable dbDatastore.readAllAsList()[query.idInt]
        }
    }
}