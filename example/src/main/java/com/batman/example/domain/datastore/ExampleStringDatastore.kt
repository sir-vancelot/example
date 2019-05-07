package com.batman.example.domain.datastore

import com.batman.batdroid.domain.datastore.BatmanDBDatastore
import com.batman.example.domain.datastore.db.ExampleDBOpenHelper
import com.batman.example.domain.datastore.db.query.ExampleStringQuery
import com.batman.example.domain.mapper.ExampleStringModelMapper
import com.batman.example.domain.model.ExampleStringModel

class ExampleStringDatastore(exampleDBOpenHelper: ExampleDBOpenHelper): BatmanDBDatastore<ExampleStringModel>(exampleDBOpenHelper, ExampleStringQuery, ExampleStringModelMapper)