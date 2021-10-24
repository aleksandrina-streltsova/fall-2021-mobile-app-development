package com.aleksandrinastreltsova.application.ui.bookmarks

import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.aleksandrinastreltsova.application.ui.base.BaseFragment
import com.aleksandrinastreltsova.application.R
import com.aleksandrinastreltsova.application.databinding.FragmentBookmarksBinding

class BookmarksFragment : BaseFragment(R.layout.fragment_bookmarks) {

    private val viewBinding by viewBinding(FragmentBookmarksBinding::bind)
    private val viewModel: BookmarksViewModel by viewModels()

}