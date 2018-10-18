package com.batman.example.infrastructure.query

import io.reactivex.Maybe

interface QueryHandler <Query, Model> {
    fun execute(query : Query) : Maybe<Model>
}
