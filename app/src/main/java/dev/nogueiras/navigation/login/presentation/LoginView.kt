package dev.nogueiras.navigation.login.presentation

interface LoginView {
    fun showErrorMessage(errorMessage: String)
    fun navigateToDashboard()
    fun navigateToRecoverPassword()
    fun navigateToConfirmCreateAccount(username: String)
}
