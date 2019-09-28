package com.greetings.desktop.adapters.domain

import com.greetings.core.notification.Email
import com.greetings.core.notification.EmailGateway
import com.greetings.core.domain.GreetingsNotifier
import com.greetings.core.domain.Employee

class DesktopGreetingsNotifier(val emailGateway: EmailGateway) : GreetingsNotifier {

    override fun sendGreetingsTo(employee: Employee,greetingsMessage: String) {
        val email = Email(
                employee.email.value,
                "Happy birthday!",
                greetingsMessage
        )
        emailGateway.send(email)
    }
}