package com.greetings.web.adapters.domain

import com.greetings.core.domain.*
import java.io.File

class PlainTextEmployeeRepository(private val file: File) : EmployeeRepository {

    override fun findAll(): List<Employee> {
        return if(file.exists()) {
            val allLines = file.readLines()
            allLines.map { map(it) }
        }
        else {
            emptyList()
        }
    }

    private fun map(text: String) : Employee {
        val data = text.trim().split(",")

        val id = EmployeeId(data[0].trim().toInt())
        val firstName = data[1].trim()
        val lastName = data[2].trim()

        val dateData = data[3].trim().split("/")
        val day = dateData[2].trim().toInt();
        val month = dateData[1].trim().toInt();
        val year = dateData[0].trim().toInt();

        val birthDate = Date(day, month, year);
        val email = Email(data[4].trim())
        val phoneNumber = PhoneNumber(data[5].trim())

        return Employee(id, firstName, lastName, birthDate, email, phoneNumber)
    }

    override fun findEmployeesWhoseBirthdayIs(date: Date): List<Employee> = findAll().filter { it.isHisBirthday() }

}