package com.batman.example.domain.command

import com.batman.example.infrastructure.datastore.ExampleStringDatastore
import com.batman.example.domain.model.ExampleStringModel
import io.reactivex.Maybe

class UpdateExampleStringCommand(val exampleStringModel: ExampleStringModel): Command()

class UpdateExampleStringCommandHandler(private val exampleStringDatastore: ExampleStringDatastore): CommandHandler<UpdateExampleStringCommand> {
    override fun execute(command: UpdateExampleStringCommand): Maybe<Unit> {
        return Maybe.fromCallable {
            exampleStringDatastore.update(command.exampleStringModel)
        }
    }
}