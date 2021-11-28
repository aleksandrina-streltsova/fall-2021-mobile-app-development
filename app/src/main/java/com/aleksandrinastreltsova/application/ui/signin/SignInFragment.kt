package com.aleksandrinastreltsova.application.ui.signin

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            object: OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    onBackButtonPressed()
                }
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.backButton.setOnClickListener {
            onBackButtonPressed()
        }
        viewBinding.signInButton.setOnClickListener {
            viewModel.signIn(
                viewBinding.emailEditText.text?.toString() ?: "",
                viewBinding.passwordEditText.text?.toString() ?: ""
            )
        }
        subscribeToFormFields()
    }

    private fun onBackButtonPressed() {
        val email = viewBinding.emailEditText.text?.toString() ?: ""
        val password = viewBinding.passwordEditText.text?.toString() ?: ""
        if (email.isBlank() && password.isBlank()) {
            findNavController().popBackStack() // TODO: hide keyboard
            return
        }
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.common_back_alert_dialog_text)
            .setNegativeButton(R.string.common_back_alert_dialog_cancel_button_text) { dialog, _ ->
                dialog?.dismiss()
            }
            .setPositiveButton(R.string.common_back_alert_dialog_ok_button_text) { _, _ ->
                findNavController().popBackStack()
            }
            .show()
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