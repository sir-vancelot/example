package com.batman.example.infrastructure.query

import com.batman.example.domain.datastore.ExampleStringDatastore
import com.batman.example.infrastructure.model.ExampleStringModel
import io.reactivex.Maybe

// <editor-fold desc="Define a execute for the execute handler">
class ReadExampleStringQuery
// </editor-fold>

// <editor-fold desc="Define a execute handler and override the execute function">
class ReadExampleStringQueryHandler(private val exampleStringDatastore: ExampleStringDatastore): QueryHandler<ReadExampleStringQuery, List<ExampleStringModel>> {
    override fun execute(query: ReadExampleStringQuery): Maybe<List<ExampleStringModel>> {
        return Maybe.fromCallable {
            exampleStringDatastore.cache
        }
    }
}
// </editor-fold>