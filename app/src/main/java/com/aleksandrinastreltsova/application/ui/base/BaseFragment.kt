package com.aleksandrinastreltsova.application.ui.base

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.aleksandrinastreltsova.application.BuildConfig
import com.aleksandrinastreltsova.application.util.logBackstack
import com.aleksandrinastreltsova.application.util.logFragmentHierarchy
import timber.log.Timber

open class BaseFragment : Fragment {

    constructor() : super()

    constructor(@LayoutRes contentLayoutId: Int) : super(contentLayoutId)

    override fun onStart() {
        super.onStart()
        if (BuildConfig.DEBUG) {
            val logTag = "NavigationInfo"
            logFragmentHierarchy(logTag)
            try {
                findNavController().logBackstack(logTag)
            } catch (error: IllegalStateException) {
                Timber.e(error)
            }
        }
    }
}