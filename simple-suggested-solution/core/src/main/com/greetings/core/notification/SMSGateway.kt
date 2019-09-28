package com.greetings.core.notification

interface SMSGateway {
    fun send(sms: SMS)
}
