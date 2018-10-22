package com.batman.example.domain.query

import io.reactivex.Maybe

interface QueryHandler <Query, Model> {
    fun execute(query : Query) : Maybe<Model>
}
