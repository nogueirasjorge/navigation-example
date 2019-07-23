package dev.nogueiras.navigation.login.infrastructure.repository

import dev.nogueiras.navigation.login.core.domain.model.User
import dev.nogueiras.navigation.login.core.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class InMemoryUserRepository : UserRepository {

    private val users = listOf(User(1L, "valid@email.com", "password"))

    override suspend fun findByEmail(email: String): User? = withContext(Dispatchers.IO) {
        users.firstOrNull { it.username == email }
    }

}