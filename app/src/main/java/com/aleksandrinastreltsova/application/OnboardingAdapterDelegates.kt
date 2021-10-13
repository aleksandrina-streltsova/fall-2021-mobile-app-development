package com.aleksandrinastreltsova.application

import com.aleksandrinastreltsova.application.databinding.ItemOnboardindTextBinding
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun onboardingTextAdapterDelegate() =
    adapterDelegateViewBinding<String, CharSequence, ItemOnboardindTextBinding>(
        viewBinding = { layoutInflater, parent ->
           ItemOnboardindTextBinding.inflate(layoutInflater, parent, false)
        },
        block = {
            bind {
                binding.textView.text = item
            }
        }
    )