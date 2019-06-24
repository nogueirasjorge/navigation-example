package dev.nogueiras.navigation.login.core.domain.action

import dev.nogueiras.navigation.login.core.domain.exception.WrongCredentialsException
import dev.nogueiras.navigation.login.core.domain.model.Credentials
import dev.nogueiras.navigation.login.core.domain.model.User
import dev.nogueiras.navigation.login.core.domain.repository.UserRepository

class Login(private val repository: UserRepository) {
    operator fun invoke(credentials: Credentials): User {
        val user = repository.findByEmail(credentials.username)
        return user?.takeIf { it.hasValidPassword(credentials.password) }
            ?: invalidUser()
    }

    private fun invalidUser(): User {
        throw WrongCredentialsException()
    }
}
