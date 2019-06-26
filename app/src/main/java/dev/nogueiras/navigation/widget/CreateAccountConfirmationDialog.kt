package dev.nogueiras.navigation.widget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.button.MaterialButton
import dev.nogueiras.navigation.R

class CreateAccountConfirmationDialog : DialogFragment() {

    private lateinit var cancelButton: MaterialButton
    private lateinit var acceptButton: MaterialButton
    private lateinit var description: TextView
    private lateinit var navigation: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.confirmation_dialog_style)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_create_account_confirmation, container, true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureNavigation()
        configureDescription(view)
        configureCancelButton(view)
        configureAcceptButton(view)
    }

    private fun configureNavigation() {
        navigation = NavHostFragment.findNavController(this)
    }

    private fun configureDescription(view: View) {
        description = view.findViewById(R.id.description)
        description.text = getString(R.string.confirmation_subtitle, extractUsernameFromArguments())
    }

    private fun configureAcceptButton(view: View) {
        acceptButton = view.findViewById(R.id.accept)
        acceptButton.setOnClickListener { navigation.navigate(R.id.action_confirmation_to_dashboard) }
    }

    private fun configureCancelButton(view: View) {
        cancelButton = view.findViewById(R.id.cancel)
        cancelButton.setOnClickListener { dismiss() }
    }

    private fun extractUsernameFromArguments(): String {
        return arguments?.let {
            CreateAccountConfirmationDialogArgs.fromBundle(it).username
        } ?: ""
    }

}