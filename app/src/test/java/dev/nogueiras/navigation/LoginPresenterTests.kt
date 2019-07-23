package dev.nogueiras.navigation

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import dev.nogueiras.navigation.login.core.domain.action.Login
import dev.nogueiras.navigation.login.core.domain.model.Credentials
import dev.nogueiras.navigation.login.core.domain.model.User
import dev.nogueiras.navigation.login.core.domain.repository.UserRepository
import dev.nogueiras.navigation.login.presentation.LoggedUser
import dev.nogueiras.navigation.login.presentation.LoginPresenter
import dev.nogueiras.navigation.login.presentation.LoginView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

class LoginPresenterTests {
    private lateinit var presenter: LoginPresenter
    private lateinit var view: LoginView
    private lateinit var users: UserRepository

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        view = mock()
        users = mock()
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @Test
    fun `login fails due to wrong credentials shows error`() = runBlocking {
        givenPresenterWithNonExistingUser()

        whenLoginButtonPressed()

        thenWrongCredentialsErrorShown()
    }

    @Test
    fun `login successful navigates to dashboard`() = runBlocking {
        givenPresenterWithExistingUser()

        whenLoginButtonPressed()

        thenNavigatesToDashboard()
    }

    @Test
    fun `navigates to recover password`() {
        givenPresenter()

        whenRecoverPasswordButtonPressed()

        thenNavigatesToRecoverPassword()

    }

    @Test
    fun `create without mail show error`() {
        givenPresenter()

        whenCreateButtonPressedWithoutUsername()

        thenErrorShown()
    }

    @Test
    fun `create with username then navigates to create confirmation`() {
        givenPresenter()

        whenCreateButtonPressedWithUsername()

        thenNavigatesToConfirmationDialog()
    }

    private suspend fun givenPresenterWithNonExistingUser() {
        givenPresenter()
        givenNonExistingUser()
    }

    private suspend fun givenPresenterWithExistingUser() {
        givenPresenter()
        givenExistingUser()
    }

    private suspend fun givenNonExistingUser() {
        whenever(users.findByEmail(InvalidCredentials.username)).thenReturn(null)
    }

    private suspend fun givenExistingUser() {
        whenever(users.findByEmail(ValidCredentials.username)).thenReturn(ValidUser)
    }

    private fun givenPresenter() {
        presenter = LoginPresenter(view, Login(users))
    }

    private fun whenCreateButtonPressedWithUsername() {
        presenter.onCreateButtonPressed(ValidUser.username)
    }

    private fun whenCreateButtonPressedWithoutUsername() {
        presenter.onCreateButtonPressed()
    }

    private fun whenLoginButtonPressed() {
        presenter.onLoginButtonPressed(ValidCredentials)
    }

    private fun whenRecoverPasswordButtonPressed() {
        presenter.onRecoverPasswordButtonPressed()
    }

    private fun thenWrongCredentialsErrorShown() {
        verify(view).showErrorMessage("Wrong credentials")
    }

    private fun thenNavigatesToDashboard() {
        verify(view).navigateToDashboard(LoggedUser(ValidUser.username))
    }

    private fun thenNavigatesToRecoverPassword() {
        verify(view).navigateToRecoverPassword()
    }

    private fun thenNavigatesToConfirmationDialog() {
        verify(view).navigateToConfirmCreateAccount(ValidUser.username)
    }

    private fun thenErrorShown() {
        verify(view).showErrorMessage("Username missing")
    }

    companion object {
        private val ValidCredentials = Credentials("valid@email.com", "some-secret-password")
        private val ValidUser = User(id = 1L, username = "valid@email.com", password = "some-secret-password")
        private val InvalidCredentials = Credentials("invalid@email.com", "some-secret-password")
    }

}
