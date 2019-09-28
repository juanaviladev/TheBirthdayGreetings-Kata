package com.greetings.web.adapters.web

import com.greetings.core.usecases.EmployeeDTO
import com.greetings.core.usecases.FindAll
import com.greetings.core.usecases.FindAllRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class FindAllEmployeesController(val findAll: FindAll) {

    @GetMapping("/employees")
    fun handle(): List<EmployeeDTO> {
        return findAll(FindAllRequest()).employees
    }

}