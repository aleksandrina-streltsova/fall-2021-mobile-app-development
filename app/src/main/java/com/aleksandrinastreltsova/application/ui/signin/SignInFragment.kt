package com.aleksandrinastreltsova.application.ui.signin

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.aleksandrinastreltsova.application.ui.base.BaseFragment
import com.aleksandrinastreltsova.application.R
import com.aleksandrinastreltsova.application.databinding.FragmentSignInBinding

class SignInFragment : BaseFragment(R.layout.fragment_sign_in) {

    private val viewBinding by viewBinding(FragmentSignInBinding::bind)
    private val viewModel: SignInViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.backButton.setOnClickListener {
            // TODO: there's also system back button...
            val email = viewBinding.emailEditText.text?.toString() ?: ""
            val password = viewBinding.passwordEditText.text?.toString() ?: ""
            if (email.isBlank() && password.isBlank()) {
                findNavController().popBackStack() // TODO: hide keyboard
                return@setOnClickListener
            }
            AlertDialog.Builder(requireContext())
                .setTitle(R.string.sign_in_back_alert_dialog_text)
                .setNegativeButton(R.string.sign_in_back_alert_dialog_cancel_button_text) { dialog, _ ->
                    dialog?.dismiss()
                }
                .setPositiveButton(R.string.sign_in_back_alert_dialog_ok_button_text) { _, _ ->
                    findNavController().popBackStack()
                }
                .show()
        }
        viewBinding.signInButton.setOnClickListener {
            viewModel.signIn(
                viewBinding.emailEditText.text?.toString() ?: "",
                viewBinding.passwordEditText.text?.toString() ?: ""
            )
        }
        subscribeToFormFields()
    }

    private fun subscribeToFormFields() {
        updateSignInButtonState(
            viewBinding.emailEditText.text?.toString(),
            viewBinding.passwordEditText.text?.toString()
        )
        viewBinding.emailEditText.doAfterTextChanged { email ->
            updateSignInButtonState(
                email?.toString(),
                viewBinding.passwordEditText.text?.toString()
            )
        }
        viewBinding.passwordEditText.doAfterTextChanged { password ->
            updateSignInButtonState(
                viewBinding.emailEditText.text?.toString(),
                password?.toString()
            )
        }
    }

    private fun updateSignInButtonState(email: String?, password: String?) {
        viewBinding.signInButton.isEnabled = !(email.isNullOrBlank() || password.isNullOrBlank())
    }

}