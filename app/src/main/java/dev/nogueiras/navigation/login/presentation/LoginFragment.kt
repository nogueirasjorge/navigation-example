package dev.nogueiras.navigation.login.presentation


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import dev.nogueiras.navigation.R
import dev.nogueiras.navigation.login.core.domain.model.Credentials


class LoginFragment : Fragment(), LoginView {

    private lateinit var presenter: LoginPresenter
    private lateinit var navigation: NavController
    private lateinit var wrongCredentials: TextView
    private lateinit var loginButton: MaterialButton
    private lateinit var createButton: MaterialButton
    private lateinit var recoverPassword: TextView
    private lateinit var usernameField: TextInputEditText
    private lateinit var passwordField: TextInputEditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        presenter = LoginViewFactory.providePresenter(this)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigation = Navigation.findNavController(view)
        loginButton = view.findViewById(R.id.login_button)
        createButton = view.findViewById(R.id.create_button)
        wrongCredentials = view.findViewById(R.id.wrong_credentials)
        recoverPassword = view.findViewById(R.id.recover_password)
        usernameField = view.findViewById(R.id.username_field)
        passwordField = view.findViewById(R.id.password_field)
        configureButtons()
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }

    override fun showErrorMessage(errorMessage: String) {
        wrongCredentials.text = errorMessage
        wrongCredentials.visibility = VISIBLE
    }

    override fun navigateToRecoverPassword() {
        navigation.navigate(R.id.action_login_to_recover)
    }

    override fun navigateToConfirmCreateAccount(username: String) {
        val action = LoginFragmentDirections.actionLoginToConfirmation(username)
        navigation.navigate(action)
    }

    override fun navigateToDashboard(loggedUser: LoggedUser) {
        val action = LoginFragmentDirections.actionLoginToDashboard(loggedUser)
        navigation.navigate(action)
    }

    private fun configureButtons() {
        loginButton.setOnClickListener { doLogin() }
        recoverPassword.setOnClickListener { presenter.onRecoverPasswordButtonPressed() }
        createButton.setOnClickListener { confirmCreateAccount() }
    }

    private fun confirmCreateAccount() {
        val username = usernameField.extractOrNull()
        presenter.onCreateButtonPressed(username)
    }

    private fun doLogin() {
        val username = usernameField.extract()
        val password = passwordField.extract()
        presenter.onLoginButtonPressed(Credentials(username, password))
    }
}

private fun TextInputEditText.extract(): String {
    return text.toString()
}

private fun TextInputEditText.extractOrNull(): String? {
    val text = text.toString()
    return if (text.isBlank() || text.isEmpty()) null else text
}
