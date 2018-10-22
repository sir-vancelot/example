package com.batman.example.domain.command

import io.reactivex.Maybe

interface CommandHandler <Command> {
    fun execute(command: Command) : Maybe<Unit>
}
