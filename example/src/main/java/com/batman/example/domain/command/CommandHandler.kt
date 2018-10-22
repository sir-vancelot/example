package com.batman.example.domain.command

import io.reactivex.Maybe

// <editor-fold desc="Define a command for the command handler">
open class Command
// </editor-fold>

// <editor-fold desc="Define a command handler for the command">
interface CommandHandler<Command> {
    fun execute(command: Command) : Maybe<Unit>
}
// </editor-fold>