package com.greetings.web.adapters.web

import com.greetings.core.usecases.*
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SendGreetingsController(val sendGreetings: SendGreetings) {

    @PostMapping("/employees/send-greetings")
    fun handle(): SendGreetingsResponse {
        return sendGreetings(SendGreetingsRequest())
    }

}