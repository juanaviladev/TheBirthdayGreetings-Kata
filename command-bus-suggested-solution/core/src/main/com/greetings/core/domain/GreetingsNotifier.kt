package com.greetings.core.domain

interface GreetingsNotifier {
    fun sendGreetingsTo(employee: Employee,greetingsMessage: String)
}

