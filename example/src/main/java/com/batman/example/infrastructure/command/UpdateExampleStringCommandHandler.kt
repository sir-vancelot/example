package com.batman.example.infrastructure.command

import com.batman.example.domain.datastore.ExampleStringDatastore
import com.batman.example.infrastructure.model.ExampleStringModel
import io.reactivex.Maybe

// <editor-fold desc="Define a command for the command handler">
data class UpdateExampleStringCommand(val exampleStringModel: ExampleStringModel)
// </editor-fold>

// <editor-fold desc="Define a command handler and override the execute function">
class UpdateExampleStringCommandHandler(private val exampleStringDatastore: ExampleStringDatastore): CommandHandler<UpdateExampleStringCommand> {
    override fun execute(command: UpdateExampleStringCommand): Maybe<Unit> {
        return Maybe.fromCallable {
            exampleStringDatastore.update(command.exampleStringModel)
        }
    }
}
// </editor-fold>