package com.batman.example.infrastructure.command

import io.reactivex.Maybe

interface CommandHandler <Command> {
    fun execute(command: Command) : Maybe<Unit>
}
