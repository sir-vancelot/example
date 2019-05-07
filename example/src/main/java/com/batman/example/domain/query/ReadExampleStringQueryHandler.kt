package com.batman.example.domain.query

import com.batman.batdroid.domain.interactor.query.BatmanReadAllQuery
import com.batman.batdroid.domain.interactor.query.datastore.BatmanReadAllQueryHandler
import com.batman.example.domain.datastore.ExampleStringDatastore
import com.batman.example.domain.model.ExampleStringModel

class ReadExampleStringQuery: BatmanReadAllQuery()

class ReadExampleStringQueryHandler(exampleStringDatastore: ExampleStringDatastore): BatmanReadAllQueryHandler<ReadExampleStringQuery, ExampleStringModel>(exampleStringDatastore)