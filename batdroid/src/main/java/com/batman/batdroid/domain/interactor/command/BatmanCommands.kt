package com.batman.batdroid.domain.interactor.command

import com.batman.batdroid.domain.model.BatmanModel

/**
 * A [BatmanCommand] is a default command intended to be used with [BatmanCommandHandlers][BatmanCommandHandler]
 * to execute asynchronous tasks via [RxJava](https://github.com/ReactiveX/RxJava) [Maybe's][io.reactivex.Maybe].
 */
open class BatmanCommand

/**
 * A [BatmanCreateCommand] is a default command intended to be used with [BatmanCommandHandlers][BatmanCommandHandler]
 * to execute asynchronous tasks via [RxJava](https://github.com/ReactiveX/RxJava) [Maybe's][io.reactivex.Maybe]
 * that CREATE database entries for a [BatmanModel].
 */
open class BatmanCreateCommand<Model: BatmanModel>(val model: Model)

/**
 * A [BatmanUpdateCommand] is a default command intended to be used with [BatmanCommandHandlers][BatmanCommandHandler]
 * to execute asynchronous tasks via [RxJava](https://github.com/ReactiveX/RxJava) [Maybe's][io.reactivex.Maybe]
 * that UPDATE database entries for a [BatmanModel].
 */
open class BatmanUpdateCommand<Model: BatmanModel>(val model: Model)

/**
 * A [BatmanDeleteCommand] is a default command intended to be used with [BatmanCommandHandlers][BatmanCommandHandler]
 * to execute asynchronous tasks via [RxJava](https://github.com/ReactiveX/RxJava) [Maybe's][io.reactivex.Maybe]
 * that DELETE database entries for a [BatmanModel].
 */
open class BatmanDeleteCommand<Model: BatmanModel>(val model: Model)