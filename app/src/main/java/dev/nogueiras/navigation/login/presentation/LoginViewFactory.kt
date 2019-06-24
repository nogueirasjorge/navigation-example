package dev.nogueiras.navigation.login.presentation

import dev.nogueiras.navigation.login.core.factory.ActionsFactory

object LoginViewFactory {
    fun providePresenter(view: LoginView): LoginPresenter {
        return LoginPresenter(view, ActionsFactory.provideLogin())
    }

}
