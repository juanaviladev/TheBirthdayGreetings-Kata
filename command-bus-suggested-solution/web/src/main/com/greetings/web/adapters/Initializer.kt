package com.greetings.web.adapters

import com.greetings.adapters.infrastructure.SimpleCommandBus
import com.greetings.core.domain.EmployeeRepository
import com.greetings.core.domain.GreetingsNotifier
import com.greetings.core.usecases.*
import com.greetings.core.utils.AppComponent
import com.greetings.core.utils.DrivenAdapter
import com.greetings.core.utils.DriverAdapter
import com.greetings.web.adapters.domain.PlainTextEmployeeRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.SpringApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.FilterType
import java.io.File


@SpringBootApplication
@ComponentScan(
        basePackages = ["com.greetings.core","com.greetings.web.adapters","com.greetings.adapters"],
        includeFilters = [ComponentScan.Filter(type = FilterType.ANNOTATION, value = [DriverAdapter::class,AppComponent::class, DrivenAdapter::class])])
class Initializer {

    @Value(value="\${repository.config.file}")
    private lateinit var repositoryFilePath : String

    @Bean
    fun repository() = PlainTextEmployeeRepository(File(repositoryFilePath))

    @Bean
    fun commandBus(findAllUseCase: FindAllUseCase,
                   sendGreetingsUseCase: SendGreetingsUseCase): CommandBus {

        val commandBus = SimpleCommandBus()

        commandBus.register(findAllUseCase, FindAllCommand())
        commandBus.register(sendGreetingsUseCase, SendGreetingsCommand())

        return commandBus
    }
}

fun main() {
    SpringApplication.run(Initializer::class.java)
}
