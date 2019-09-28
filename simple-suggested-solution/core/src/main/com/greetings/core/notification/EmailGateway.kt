package com.greetings.core.notification

interface EmailGateway {
    fun send(email: Email)
}

