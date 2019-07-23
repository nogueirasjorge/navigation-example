package dev.nogueiras.navigation.login.core.domain.repository

import dev.nogueiras.navigation.login.core.domain.model.User

interface UserRepository {
    suspend fun findByEmail(email: String): User?
}
