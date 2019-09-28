package com.greetings.desktop.adapters.notification

import com.greetings.core.logging.Logger
import com.greetings.core.notification.Email
import com.greetings.core.notification.EmailGateway
import org.simplejavamail.email.EmailBuilder
import org.simplejavamail.mailer.Mailer
import org.simplejavamail.mailer.MailerBuilder
import org.simplejavamail.mailer.config.TransportStrategy

class SMTPEmailGateway(private val logger: Logger) : EmailGateway {

    private val from = "Greetings Kata"
    private val fromAddress = "greetings@kata.com"
    private val host = "smtp.gmail.com"
    private val port = 587
    private val user = "c"
    private val pass = ""

    private val mailer : Mailer = MailerBuilder
            .withSMTPServer(host, port, user, pass)
            .withTransportStrategy(TransportStrategy.SMTP_TLS)
            .buildMailer()

    override fun send(email: Email) {
        logger.d("Sending email: $email")
        val preparedMail = EmailBuilder.startingBlank()
                .to(email.to)
                .from(from, fromAddress)
                .withSubject(email.subject)
                .withPlainText(email.body)
                .buildEmail()

        mailer.sendMail(preparedMail)
        logger.d("Email sent: $email")
    }

}