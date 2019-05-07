package com.batman.batdroid.domain.interactor.command

import io.reactivex.Maybe

/**
 * The [BatmanCommandHandler] is used to [execute] asynchronous tasks in the form of a [RxJava](https://github.com/ReactiveX/RxJava)
 * [Maybe].
 */
interface BatmanCommandHandler<Command : BatmanCommand> {
    /**
     * Defines what should occur by default when a [BatmanCommand] is executed.
     */
    fun execute(command: Command): Maybe<Unit>
}