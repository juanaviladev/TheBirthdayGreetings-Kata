package com.greetings.core.notification

data class SMS(
        val from: String,
        val destinationNumber: String,
        val body: String
)