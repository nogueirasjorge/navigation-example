package dev.nogueiras.navigation.login.infrastructure.repository

import dev.nogueiras.navigation.login.core.domain.model.User
import dev.nogueiras.navigation.login.core.domain.repository.UserRepository

class InMemoryUserRepository : UserRepository {

    private val users = listOf(User(1L, "valid@email.com", "password"))

    override fun findByEmail(email: String): User? {
        return users.firstOrNull { it.username == email }
    }

}

