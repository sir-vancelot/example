package com.batman.example.domain.query

import io.reactivex.Maybe

// <editor-fold desc="Define a query for the query handler">
interface Query
// </editor-fold>

// <editor-fold desc="Define a handler for the query">
interface QueryHandler<Query, Model> {
    fun execute(query : Query) : Maybe<Model?>
}
// </editor-fold>