package com.batman.batdroid.domain.interactor.query.repository

import com.batman.batdroid.domain.datastore.BatmanDBDatastore
import com.batman.batdroid.domain.interactor.query.BatmanReadAllByOwnerIdQuery
import com.batman.batdroid.domain.interactor.query.BatmanReadAllQuery
import com.batman.batdroid.domain.interactor.query.BatmanReadByIdQuery
import com.batman.batdroid.domain.model.BatmanModel
import com.batman.batdroid.domain.repository.BatmanRepository
import io.reactivex.Maybe

/**
 * This [BatmanEncryptedReadAllQueryHandler] is a [BatmanQueryHandler][com.batman.batdroid.domain.interactor.query.BatmanQueryHandler]
 * which is used to read all [BatmanModel's][BatmanModel] from a [BatmanRepository].
 */
abstract class BatmanReadAllQueryHandler<Query : BatmanReadAllQuery, ParentModel : BatmanModel,
        ParentDBDatastore: BatmanDBDatastore<ParentModel>>(protected val repository: BatmanRepository<ParentModel, ParentDBDatastore>) {
    /**
     * Defines what should occur by default when a [BatmanReadAllQuery] is queried to a [Map]
     */
    open fun queryMap(query: Query): Maybe<HashMap<String, ParentModel>> {
        return Maybe.fromCallable {
            return@fromCallable repository.readAllAsMap()
        }
    }

    /**
     * Defines what should occur by default when a [BatmanReadAllQuery] is queried to a [List]
     */
    open fun queryList(query: Query): Maybe<List<ParentModel>> {
        return Maybe.fromCallable {
            return@fromCallable repository.readAllAsList()
        }
    }
}

/**
 * This [BatmanReadAllByOwnerIdQueryHandler] is a [BatmanQueryHandler][com.batman.batdroid.domain.interactor.query.BatmanQueryHandler]
 * which is used to read all [BatmanModel's][BatmanModel] by their [ownerId][com.batman.batdroid.domain.datastore.db.query.BatmanDBQuery.ownerIds]
 * from a [BatmanRepository].
 */
abstract class BatmanReadAllByOwnerIdQueryHandler<Query : BatmanReadAllByOwnerIdQuery, ParentModel : BatmanModel,
        ParentDBDatastore: BatmanDBDatastore<ParentModel>>(protected val repository: BatmanRepository<ParentModel, ParentDBDatastore>) {
    /**
     * Defines what should occur by default when a [BatmanReadAllByOwnerIdQuery] is queried to a [Map]
     */
    open fun queryMap(query: Query): Maybe<HashMap<String, ParentModel>> {
        return Maybe.create {
            val models = repository.readMapByOwnerId(query.ownerId, query.ownerIdKey)
            it.onSuccess(models)
        }
    }

    /**
     * Defines what should occur by default when a [BatmanReadAllByOwnerIdQuery] is queried to a [List]
     */
    open fun queryList(query: Query): Maybe<List<ParentModel>> {
        return Maybe.create {
            val models = repository.readListByOwnerId(query.ownerId, query.ownerIdKey)
            it.onSuccess(models)
        }
    }
}

/**
 * This [BatmanReadAllByOwnerIdQueryHandler] is a [BatmanQueryHandler][com.batman.batdroid.domain.interactor.query.BatmanQueryHandler]
 * which is used to read a [BatmanModel] by its [identityColumn][com.batman.batdroid.domain.datastore.db.query.BatmanDBQuery.identityColumn]
 * from a [BatmanRepository].
 */
abstract class BatmanReadByIdQueryHandler<Query : BatmanReadByIdQuery, ParentModel : BatmanModel,
        ParentDBDatastore: BatmanDBDatastore<ParentModel>>(protected val repository: BatmanRepository<ParentModel, ParentDBDatastore>) {
    /**
     * Defines what should occur by default when a [BatmanReadByIdQuery] is queried to a [Map]
     */
    open fun queryMap(query: Query): Maybe<ParentModel> {
        return Maybe.fromCallable {
            if (query.idString == null) return@fromCallable null
            else return@fromCallable repository.readAllAsMap()[query.idString]
        }
    }

    /**
     * Defines what should occur by default when a [BatmanReadByIdQuery] is queried to a [List]
     */
    open fun queryList(query: Query): Maybe<ParentModel> {
        return Maybe.fromCallable {
            if (query.idInt == null) return@fromCallable null
            else return@fromCallable repository.readAllAsList()[query.idInt]
        }
    }
}