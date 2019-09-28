package com.greetings.core.usecases

import com.greetings.core.domain.EmployeeRepository
import com.greetings.core.utils.AppComponent

@AppComponent
class FindAllUseCase(private val employeeRepository: EmployeeRepository) : CommandHandler<FindAllCommand,FindAllResponse> {

    override fun invoke(command: FindAllCommand): FindAllResponse {
        val employees = employeeRepository.findAll()
        return FindAllResponse(employees.map { it.toTransfer() })
    }
}

class FindAllCommand : Command<FindAllResponse>

class FindAllResponse(val employees: List<EmployeeDTO>) : Response