package com.batman.batdroid.domain.interactor.command.datastore

import com.batman.batdroid.domain.datastore.BatmanDBDatastore
import com.batman.batdroid.domain.interactor.command.BatmanCreateCommand
import com.batman.batdroid.domain.interactor.command.BatmanDeleteCommand
import com.batman.batdroid.domain.interactor.command.BatmanUpdateCommand
import com.batman.batdroid.domain.model.BatmanModel
import io.reactivex.Maybe

/**
 * This [BatmanCreateCommandHandler] is a [BatmanCommandHandler][com.batman.batdroid.domain.interactor.command.BatmanCommandHandler]
 * which is used to CREATE a [BatmanModel] in a [BatmanDBDatastore].
 */
abstract class BatmanCreateCommandHandler<Model: BatmanModel, Command: BatmanCreateCommand<Model>>(protected val dbDatastore: BatmanDBDatastore<Model>) {
    /**
     * Defines what should occur by default when a [BatmanCreateCommand] is executed.
     */
    open fun execute(command: Command) : Maybe<Unit> {
        return Maybe.create{
            dbDatastore.create(command.model)
            it.onSuccess(Unit)
        }
    }
}

/**
 * This [BatmanUpdateCommandHandler] is a [BatmanCommandHandler][com.batman.batdroid.domain.interactor.command.BatmanCommandHandler]
 * which is used to UPDATE a [BatmanModel] in a [BatmanDBDatastore].
 */
abstract class BatmanUpdateCommandHandler<Model: BatmanModel, Command: BatmanUpdateCommand<Model>>(protected val dbDatastore: BatmanDBDatastore<Model>) {
    /**
     * Defines what should occur by default when a [BatmanUpdateCommand] is executed.
     */
    open fun execute(command: Command) : Maybe<Unit> {
        return Maybe.create{
            dbDatastore.update(command.model)
            it.onSuccess(Unit)
        }
    }
}

/**
 * This [BatmanDeleteCommandHandler] is a [BatmanCommandHandler][com.batman.batdroid.domain.interactor.command.BatmanCommandHandler]
 * which is used to DELETE a [BatmanModel] from a [BatmanDBDatastore].
 */
abstract class BatmanDeleteCommandHandler<Model: BatmanModel, Command: BatmanDeleteCommand<Model>>(protected val dbDatastore: BatmanDBDatastore<Model>) {
    /**
     * Defines what should occur by default when a [BatmanDeleteCommand] is executed.
     */
    open fun execute(command: Command) : Maybe<Unit> {
        return Maybe.create{
            dbDatastore.delete(command.model)
            it.onSuccess(Unit)
        }
    }
}