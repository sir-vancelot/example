package com.batman.example.domain.command

import com.batman.batdroid.domain.interactor.command.BatmanUpdateCommand
import com.batman.batdroid.domain.interactor.command.datastore.BatmanUpdateCommandHandler
import com.batman.example.domain.datastore.ExampleStringDatastore
import com.batman.example.domain.model.ExampleStringModel

class UpdateExampleStringCommand(exampleStringModel: ExampleStringModel): BatmanUpdateCommand<ExampleStringModel>(exampleStringModel)

class UpdateExampleStringCommandHandler(exampleStringDatastore: ExampleStringDatastore): BatmanUpdateCommandHandler<ExampleStringModel, UpdateExampleStringCommand>(exampleStringDatastore)
