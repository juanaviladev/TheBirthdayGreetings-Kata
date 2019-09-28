package com.greetings.core.domain

import com.greetings.core.exceptions.DomainException
import java.util.regex.Pattern.compile

class Email(val value: String) {

    private val emailRegex = compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    )

    init {
        if(!emailRegex.matcher(value).matches())
            throw DomainException("Email is not well formed")
    }

    override fun toString(): String {
        return value
    }
}