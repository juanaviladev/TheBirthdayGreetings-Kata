package com.greetings.core.domain

import com.greetings.core.exceptions.DomainException
import java.util.regex.Pattern

class PhoneNumber(val value: String) {

    private val emailRegex = Pattern.compile("(([+][(]?[0-9]{1,3}[)]?)|([(]?[0-9]{4}[)]?))\\s*[)]?[-\\s\\.]?[(]?[0-9]{1,3}[)]?([-\\s\\.]?[0-9]{3})([-\\s\\.]?[0-9]{3,4})")

    init {
        if(!emailRegex.matcher(value).matches())
            throw DomainException("Phone number is not well formed")
    }

    override fun toString(): String {
        return value
    }

}