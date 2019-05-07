package com.batman.batdroid.domain.interactor.query

/**
 * A [BatmanQuery] is a default query intended to be used with [BatmanQueryHandlers][BatmanQueryHandler]
 * to execute asynchronous tasks via [RxJava](https://github.com/ReactiveX/RxJava) [Maybe's][io.reactivex.Maybe].
 */
open class BatmanQuery

/**
 * A [BatmanReadAllQuery] is a default command intended to be used with [BatmanQueryHandlers][BatmanQueryHandler]
 * to execute asynchronous tasks via [RxJava](https://github.com/ReactiveX/RxJava) [Maybe's][io.reactivex.Maybe]
 * to read all [BatmanModels][com.batman.batdroid.domain.model.BatmanModel] from a database.
 */
open class BatmanReadAllQuery

/**
 * A [BatmanReadAllByOwnerIdQuery] is a default command intended to be used with [BatmanQueryHandlers][BatmanQueryHandler]
 * to execute asynchronous tasks via [RxJava](https://github.com/ReactiveX/RxJava) [Maybe's][io.reactivex.Maybe]
 * to read all [BatmanModels][com.batman.batdroid.domain.model.BatmanModel] by with a matching [ownerId][com.batman.batdroid.domain.datastore.db.query.BatmanDBQuery.ownerIds]
 * from a database.
 */
open class BatmanReadAllByOwnerIdQuery(val ownerId: String, val ownerIdKey: String)

/**
 * A [BatmanReadByIdQuery] is a default command intended to be used with [BatmanQueryHandlers][BatmanQueryHandler]
 * to execute asynchronous tasks via [RxJava](https://github.com/ReactiveX/RxJava) [Maybe's][io.reactivex.Maybe]
 * to read a [BatmanModel][com.batman.batdroid.domain.model.BatmanModel] by by its [identityColumn][com.batman.batdroid.domain.datastore.db.query.BatmanDBQuery.identityColumn]
 * from a database.
 */
open class BatmanReadByIdQuery(val idString: String? = null, val idInt: Int? = null)