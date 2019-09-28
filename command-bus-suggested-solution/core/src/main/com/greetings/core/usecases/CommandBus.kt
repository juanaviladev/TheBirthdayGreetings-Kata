package com.greetings.core.usecases

interface CommandBus {
    fun <T : Command<R>,R: Response> handle(command: T) : R
}