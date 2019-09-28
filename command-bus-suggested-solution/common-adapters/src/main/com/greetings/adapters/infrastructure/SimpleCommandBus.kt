package com.greetings.adapters.infrastructure

import com.greetings.core.usecases.Command
import com.greetings.core.usecases.CommandBus
import com.greetings.core.usecases.CommandHandler
import com.greetings.core.usecases.Response
import com.greetings.core.utils.DriverAdapter

@DriverAdapter
class SimpleCommandBus : CommandBus {

    override fun <T : Command<R>, R : Response> handle(command: T): R {
        val handler = commandsMap[command::class.java]
        return (handler as CommandHandler<T, R>).invoke(command)
    }

    private val commandsMap =
            mutableMapOf<Class<out Command<out Response>>, CommandHandler<out Command<out Response>, out Response>>()

    fun <C : Command<R>, R : Response> register(commandHandler: CommandHandler<C, R>, command: Command<R>) {
        commandsMap[command::class.java] = commandHandler
    }

}