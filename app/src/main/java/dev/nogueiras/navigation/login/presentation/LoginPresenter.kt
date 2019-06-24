package dev.nogueiras.navigation.login.presentation

import dev.nogueiras.navigation.login.core.domain.action.Login
import dev.nogueiras.navigation.login.core.domain.exception.WrongCredentialsException
import dev.nogueiras.navigation.login.core.domain.model.Credentials

class LoginPresenter(private val view: LoginView, private val login: Login) {
    fun onLoginButtonPressed(credentials: Credentials) {
        try {
            val user = login(credentials)
            view.navigateToDashboard()
        } catch (error: WrongCredentialsException) {
            view.showErrorMessage("Wrong credentials")
        }
    }

    fun onRecoverPasswordButtonPressed() {
        view.navigateToRecoverPassword()
    }

    fun onCreateButtonPressed(username: String? = null) {
        username?.let {
            view.navigateToConfirmCreateAccount(it)
        } ?: view.showErrorMessage("Username missing")
    }

}
