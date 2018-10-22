package com.batman.example.domain.query

import com.batman.example.domain.datastore.ExampleStringDatastore
import com.batman.example.domain.model.ExampleStringModel
import io.reactivex.Maybe

class ReadExampleStringQuery: Query

class ReadExampleStringQueryHandler(private val exampleStringDatastore: ExampleStringDatastore): QueryHandler<ReadExampleStringQuery, List<ExampleStringModel>> {
    override fun execute(query: ReadExampleStringQuery): Maybe<List<ExampleStringModel>?> {
        return Maybe.fromCallable {
            exampleStringDatastore.cache
        }
    }
}