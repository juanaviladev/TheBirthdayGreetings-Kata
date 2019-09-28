package com.greetings.core.notification

data class Email(
        val to : String,
        val subject: String,
        val body : String
)