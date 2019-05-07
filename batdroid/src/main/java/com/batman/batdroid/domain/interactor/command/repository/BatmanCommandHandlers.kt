package com.batman.batdroid.domain.interactor.command.repository

import com.batman.batdroid.domain.datastore.BatmanDBDatastore
import com.batman.batdroid.domain.interactor.command.BatmanCreateCommand
import com.batman.batdroid.domain.interactor.command.BatmanDeleteCommand
import com.batman.batdroid.domain.interactor.command.BatmanUpdateCommand
import com.batman.batdroid.domain.model.BatmanModel
import com.batman.batdroid.domain.repository.BatmanRepository
import io.reactivex.Maybe

/**
 * This [BatmanCreateCommandHandler] is a [BatmanCommandHandler][com.batman.batdroid.domain.interactor.command.BatmanCommandHandler]
 * which is used to CREATE a [BatmanModel] in a [BatmanRepository].
 */
abstract class BatmanCreateCommandHandler<ParentModel: BatmanModel, Command: BatmanCreateCommand<ParentModel>,
        ParentDBDatastore: BatmanDBDatastore<ParentModel>>(protected val repository: BatmanRepository<ParentModel, ParentDBDatastore>) {
    /**
     * Defines what should occur by default when a [BatmanCreateCommand] is executed.
     */
    open fun execute(command: Command) : Maybe<Unit> {
        return Maybe.create{
            repository.create(command.model)
            it.onSuccess(Unit)
        }
    }
}

/**
 * This [BatmanUpdateCommandHandler] is a [BatmanCommandHandler][com.batman.batdroid.domain.interactor.command.BatmanCommandHandler]
 * which is used to UPDATE a [BatmanModel] in a [BatmanRepository].
 */
abstract class BatmanUpdateCommandHandler<ParentModel: BatmanModel, Command: BatmanUpdateCommand<ParentModel>,
        ParentDBDatastore: BatmanDBDatastore<ParentModel>>(protected val repository: BatmanRepository<ParentModel, ParentDBDatastore>) {
    /**
     * Defines what should occur by default when a [BatmanUpdateCommand] is executed.
     */
    open fun execute(command: Command) : Maybe<Unit> {
        return Maybe.create{
            repository.update(command.model)
            it.onSuccess(Unit)
        }
    }
}

/**
 * This [BatmanDeleteCommandHandler] is a [BatmanCommandHandler][com.batman.batdroid.domain.interactor.command.BatmanCommandHandler]
 * which is used to DELETE a [BatmanModel] from a [BatmanRepository].
 */
abstract class BatmanDeleteCommandHandler<ParentModel: BatmanModel, Command: BatmanDeleteCommand<ParentModel>,
        ParentDBDatastore: BatmanDBDatastore<ParentModel>>(protected val repository: BatmanRepository<ParentModel, ParentDBDatastore>) {
    /**
     * Defines what should occur by default when a [BatmanDeleteCommand] is executed.
     */
    open fun execute(command: Command) : Maybe<Unit> {
        return Maybe.create{
            repository.delete(command.model)
            it.onSuccess(Unit)
        }
    }
}