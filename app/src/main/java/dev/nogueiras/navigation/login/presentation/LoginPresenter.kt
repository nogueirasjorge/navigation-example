package dev.nogueiras.navigation.login.presentation

import dev.nogueiras.navigation.login.core.domain.action.Login
import dev.nogueiras.navigation.login.core.domain.exception.WrongCredentialsException
import dev.nogueiras.navigation.login.core.domain.model.Credentials
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class LoginPresenter(private val view: LoginView, private val login: Login) {

    private val scope = CoroutineScope(Dispatchers.Main)

    fun onLoginButtonPressed(credentials: Credentials) {
        scope.launch(Dispatchers.IO) {
            try {
                val user = login(credentials)
                view.navigateToDashboard(LoggedUser(user.username))
            } catch (error: WrongCredentialsException) {
                view.showErrorMessage("Wrong credentials")
            }
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

    fun onPause() {
        scope.cancel()
    }

}
