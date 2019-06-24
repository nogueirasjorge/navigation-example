package dev.nogueiras.navigation.login.core.domain.model

class User(val id: Long, val username: String, private val password: String) {
    fun hasValidPassword(password: String): Boolean {
        return password == this.password
    }
}
