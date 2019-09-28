package com.greetings.core.usecases

import com.greetings.core.domain.EmployeeRepository
import com.greetings.core.utils.AppComponent

interface FindAll {
    operator fun invoke(params: FindAllRequest) : FindAllResponse
}

@AppComponent
internal class FindAllUseCase(private val employeeRepository: EmployeeRepository) : FindAll {

    override fun invoke(params: FindAllRequest): FindAllResponse {
        val employees = employeeRepository.findAll()
        return FindAllResponse(employees.map { it.toTransfer() })
    }
}

class FindAllRequest()

class FindAllResponse(val employees: List<EmployeeDTO>)