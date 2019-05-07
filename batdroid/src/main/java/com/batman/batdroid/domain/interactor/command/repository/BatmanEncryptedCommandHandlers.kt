package com.batman.batdroid.domain.interactor.command.repository

import com.batman.batdroid.domain.datastore.BatmanEncryptedDBDatastore
import com.batman.batdroid.domain.interactor.command.BatmanCreateCommand
import com.batman.batdroid.domain.interactor.command.BatmanDeleteCommand
import com.batman.batdroid.domain.interactor.command.BatmanUpdateCommand
import com.batman.batdroid.domain.model.BatmanModel
import com.batman.batdroid.domain.repository.BatmanEncryptedRepository
import io.reactivex.Maybe

/**
 * This [BatmanEncryptedCreateCommandHandler] is a [BatmanCommandHandler][com.batman.batdroid.domain.interactor.command.BatmanCommandHandler]
 * which is used to CREATE a [BatmanModel] in a [BatmanEncryptedRepository].
 */
abstract class BatmanEncryptedCreateCommandHandler<ParentModel: BatmanModel, Command: BatmanCreateCommand<ParentModel>,
        ParentDBDatastore: BatmanEncryptedDBDatastore<ParentModel>>(protected val repository: BatmanEncryptedRepository<ParentModel, ParentDBDatastore>) {
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
 * This [BatmanEncryptedUpdateCommandHandler] is a [BatmanCommandHandler][com.batman.batdroid.domain.interactor.command.BatmanCommandHandler]
 * which is used to UPDATE a [BatmanModel] in a [BatmanEncryptedRepository].
 */
abstract class BatmanEncryptedUpdateCommandHandler<ParentModel: BatmanModel, Command: BatmanUpdateCommand<ParentModel>,
        ParentDBDatastore: BatmanEncryptedDBDatastore<ParentModel>>(protected val repository: BatmanEncryptedRepository<ParentModel, ParentDBDatastore>) {
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
 * This [BatmanEncryptedDeleteCommandHandler] is a [BatmanCommandHandler][com.batman.batdroid.domain.interactor.command.BatmanCommandHandler]
 * which is used to CREATE a [BatmanModel] in a [BatmanEncryptedRepository].
 */
abstract class BatmanEncryptedDeleteCommandHandler<ParentModel: BatmanModel, Command: BatmanDeleteCommand<ParentModel>,
        ParentDBDatastore: BatmanEncryptedDBDatastore<ParentModel>>(protected val repository: BatmanEncryptedRepository<ParentModel, ParentDBDatastore>) {
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