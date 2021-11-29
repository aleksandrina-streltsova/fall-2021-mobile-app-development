package com.aleksandrinastreltsova.application.ui.news

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.aleksandrinastreltsova.application.R
import com.aleksandrinastreltsova.application.databinding.FragmentNewsBinding
import com.aleksandrinastreltsova.application.ui.base.BaseFragment
import dev.chrisbanes.insetter.applyInsetter

class NewsFragment : BaseFragment(R.layout.fragment_news) {

    private val viewBinding by viewBinding(FragmentNewsBinding::bind)
    private val viewModel: NewsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.toolbar.applyInsetter {
            type(statusBars = true) { margin() }
        }
    }
}