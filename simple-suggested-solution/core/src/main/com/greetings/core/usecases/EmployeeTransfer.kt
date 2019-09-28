package com.greetings.core.usecases

import com.greetings.core.domain.Employee

data class EmployeeDTO(
        val id : String,
        val firstName : String,
        val lastName : String,
        val birthDate: String,
        val birthdayToday : Boolean,
        val email: String,
        val phoneNumber: String
)

fun Employee.toTransfer() : EmployeeDTO {
    return EmployeeDTO(
            id.toString(),
            firstName,
            lastName,
            "${birthDate.year()}-${birthDate.month()}-${birthDate.day()}",
            isHisBirthday(),
            email.toString(),
            phoneNumber.toString()
    )
}