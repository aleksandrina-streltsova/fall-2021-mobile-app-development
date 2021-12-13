package com.aleksandrinastreltsova.application.ui.emailconfirmation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.aleksandrinastreltsova.application.R
import com.aleksandrinastreltsova.application.databinding.FragmentEmailConfirmationBinding
import com.aleksandrinastreltsova.application.ui.base.BaseFragment
import com.aleksandrinastreltsova.application.util.setTimerHint
import dev.chrisbanes.insetter.applyInsetter

class EmailConfirmationFragment : BaseFragment(R.layout.fragment_email_confirmation) {

    private val viewBinding by viewBinding(FragmentEmailConfirmationBinding::bind)
    private val viewModel: EmailConfirmationViewModel by viewModels()

    private val timer = createTimer(10000L, 1000L)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.backButton.applyInsetter {
            type(statusBars = true) { margin() }
        }
        viewBinding.openEmailButton.applyInsetter {
            type(navigationBars = true) { margin() }
        }
        viewBinding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
//        viewBinding.openEmailButton.setOnClickListener {
//            startActivity(Intent(Intent.ACTION_VIEW)
//                .setType("plain/text")
//                .setData(Uri.parse("test@gmail.com"))
//                .setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail"))
//        }
        viewBinding.sendCode.setOnClickListener {
            // TODO: send code
            viewBinding.sendCode.isEnabled = false
            timer.start()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
    }

    override fun onPause() {
        super.onPause()
        timer.cancel()
    }

    private fun createTimer(millisInFuture: Long, countDownTimer: Long): CountDownTimer =
        object : CountDownTimer(millisInFuture, countDownTimer) {
            override fun onTick(millisUntilFinished: Long) {
                viewBinding.sendAgainTimer.isVisible = true
                viewBinding.sendAgainTimer.setTimerHint(millisUntilFinished / 1000L)
            }

            override fun onFinish() {
                viewBinding.sendCode.isEnabled = true
                viewBinding.sendAgainTimer.isVisible = false
            }

        }
}