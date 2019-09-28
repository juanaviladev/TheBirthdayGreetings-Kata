package com.greetings.core.usecases

import com.greetings.core.domain.Date.Companion.today
import com.greetings.core.domain.EmployeeRepository
import com.greetings.core.domain.GreetingsNotifier
import com.greetings.core.utils.AppComponent

@AppComponent
class SendGreetingsUseCase(private val employeeRepository: EmployeeRepository,
                    private val greetingsNotifier: GreetingsNotifier)
    : CommandHandler<SendGreetingsCommand,SendGreetingsResponse>
{

    override fun invoke(params: SendGreetingsCommand): SendGreetingsResponse {
        val employees = employeeRepository.findEmployeesWhoseBirthdayIs(today())
        employees.forEach { employee ->
            greetingsNotifier.sendGreetingsTo(employee,
                    "Happy birthday ${employee.firstName}")
        }
        return SendGreetingsResponse(employees.size)
    }
}

class SendGreetingsCommand : Command<SendGreetingsResponse>

class SendGreetingsResponse(val sentNotifications: Int) : Response