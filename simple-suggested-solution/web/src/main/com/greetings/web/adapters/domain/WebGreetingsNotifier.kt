package com.greetings.web.adapters.domain

import com.greetings.core.domain.GreetingsNotifier
import com.greetings.core.notification.SMS
import com.greetings.core.notification.SMSGateway
import com.greetings.core.domain.Employee
import com.greetings.core.utils.DrivenAdapter

@DrivenAdapter
class WebGreetingsNotifier(private val smsGateway: SMSGateway) : GreetingsNotifier {

    override fun sendGreetingsTo(employee: Employee,greetingsMessage: String) {
        val sms = SMS(
                "Greetings-Kata",
                employee.phoneNumber.value,
                greetingsMessage
        )
        smsGateway.send(sms)
    }
}