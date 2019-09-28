package com.greetings.web.adapters.web

import com.greetings.core.usecases.*
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SendGreetingsController(val commandBus: CommandBus) {

    @PostMapping("/employees/send-greetings")
    fun handle(): SendGreetingsResponse {
        return commandBus.handle(SendGreetingsCommand())
    }

}