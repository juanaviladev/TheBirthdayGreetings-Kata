package com.greetings.core.usecases

interface CommandHandler<C : Command<R>, R: Response> {
    operator fun invoke(command: C) : R
}

interface Command<R: Response>
interface Response