package com.aleksandrinastreltsova.application

import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.aleksandrinastreltsova.application.databinding.FragmentMainBinding

class MainFragment : BaseFragment(R.layout.fragment_main) {

    private val viewBinding by viewBinding(FragmentMainBinding::bind)
    private val viewModel: SignInViewModel by viewModels()

}