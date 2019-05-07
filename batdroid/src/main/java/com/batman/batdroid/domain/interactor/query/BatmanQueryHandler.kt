package com.batman.batdroid.domain.interactor.query

import io.reactivex.Maybe

/**
 * The [BatmanQueryHandler] is used to [query] asynchronous tasks in the form of a [RxJava](https://github.com/ReactiveX/RxJava)
 * [Maybe].
 */
interface BatmanQueryHandler<Query : BatmanQuery, Model : Any> {
    /**
     * Defines what should occur by default when a [BatmanQuery] is queried.
     */
    fun query(query: Query): Maybe<Model>
}