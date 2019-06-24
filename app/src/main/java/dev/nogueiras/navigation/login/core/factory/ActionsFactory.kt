package dev.nogueiras.navigation.login.core.factory

import dev.nogueiras.navigation.login.core.domain.action.Login
import dev.nogueiras.navigation.login.infrastructure.repository.InMemoryUserRepository

object ActionsFactory {
    fun provideLogin(): Login {
        return Login(InMemoryUserRepository())
    }

}
