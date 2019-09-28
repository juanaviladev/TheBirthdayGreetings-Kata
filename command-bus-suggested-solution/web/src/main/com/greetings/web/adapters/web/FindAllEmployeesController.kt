package com.greetings.web.adapters.web

import com.greetings.core.usecases.CommandBus
import com.greetings.core.usecases.EmployeeDTO
import com.greetings.core.usecases.FindAllCommand
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class FindAllEmployeesController(val commandBus: CommandBus) {

    @GetMapping("/employees")
    fun handle(): List<EmployeeDTO> {
        return commandBus.handle(FindAllCommand()).employees
    }

}