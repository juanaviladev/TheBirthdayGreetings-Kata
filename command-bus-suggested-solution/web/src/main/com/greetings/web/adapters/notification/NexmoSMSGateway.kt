package com.greetings.web.adapters.notification

import com.greetings.core.notification.SMS
import com.greetings.core.notification.SMSGateway
import com.greetings.core.utils.DrivenAdapter
import com.greetings.core.logging.Logger
import com.nexmo.client.NexmoClient
import com.nexmo.client.sms.messages.TextMessage

@DrivenAdapter
class NexmoSMSGateway(val logger: Logger) : SMSGateway {

    private val API_KEY: String = ""
    private val API_SECRET: String = ""

    val client = NexmoClient.builder()
            .apiKey(API_KEY)
            .apiSecret(API_SECRET)
            .build()

    override fun send(sms: SMS) {
        logger.d("Sending SMS: $sms")
        val responses = client.smsClient.submitMessage(TextMessage(
                sms.from,
                sms.destinationNumber,
                sms.body)
        )
        logger.d("SMS sent: ${responses.messages.first()}")
    }
}