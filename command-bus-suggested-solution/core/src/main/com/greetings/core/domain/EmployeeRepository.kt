package com.greetings.core.domain

interface EmployeeRepository {
    fun findAll() : List<Employee>
    fun findEmployeesWhoseBirthdayIs(date: Date) : List<Employee>
}