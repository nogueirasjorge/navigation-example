package dev.nogueiras.navigation.login.core.domain.action

import dev.nogueiras.navigation.login.core.domain.exception.WrongCredentialsException
import dev.nogueiras.navigation.login.core.domain.model.Credentials
import dev.nogueiras.navigation.login.core.domain.model.User
import dev.nogueiras.navigation.login.core.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Login(private val repository: UserRepository) {
    suspend operator fun invoke(credentials: Credentials): User {
        val user = repository.findByEmail(credentials.username)
        return validateUser(user, credentials)
    }

    private suspend fun validateUser(user: User?, credentials: Credentials) = withContext(Dispatchers.IO) {
        user?.takeIf { it.hasValidPassword(credentials.password) } ?: invalidUser()
    }
}

private fun invalidUser(): User {
    throw WrongCredentialsException()
}
