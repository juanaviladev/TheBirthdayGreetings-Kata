package com.greetings.core.usecases

import com.greetings.core.domain.Date.Companion.today
import com.greetings.core.domain.EmployeeRepository
import com.greetings.core.domain.GreetingsNotifier
import com.greetings.core.utils.AppComponent

interface SendGreetings {
    operator fun invoke(params: SendGreetingsRequest) : SendGreetingsResponse
}

@AppComponent
internal class SendGreetingsUseCase(private val employeeRepository: EmployeeRepository,
                    private val greetingsNotifier: GreetingsNotifier)
    : SendGreetings
{

    override fun invoke(params: SendGreetingsRequest): SendGreetingsResponse {
        val employees = employeeRepository.findEmployeesWhoseBirthdayIs(today())
        employees.forEach { employee ->
            greetingsNotifier.sendGreetingsTo(employee,
                    "Happy birthday ${employee.firstName}")
        }
        return SendGreetingsResponse(employees.size)
    }
}

class SendGreetingsRequest

class SendGreetingsResponse(val sentNotifications: Int)