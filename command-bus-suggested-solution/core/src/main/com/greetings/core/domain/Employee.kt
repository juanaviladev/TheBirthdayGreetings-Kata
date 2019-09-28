package com.greetings.core.domain

class Employee(
        val id : EmployeeId,
        val firstName : String,
        val lastName : String,
        val birthDate: Date,
        val email: Email,
        val phoneNumber: PhoneNumber
) {

    fun isHisBirthday() : Boolean = birthDate.sameDayAndMonthAs(Date.today())

}