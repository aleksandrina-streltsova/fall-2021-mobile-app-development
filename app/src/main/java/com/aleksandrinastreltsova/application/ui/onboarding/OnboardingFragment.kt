package com.aleksandrinastreltsova.application.ui.onboarding

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.aleksandrinastreltsova.application.R
import com.aleksandrinastreltsova.application.databinding.FragmentOnboardingBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import dev.chrisbanes.insetter.applyInsetter
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect
import kotlin.math.abs

class OnboardingFragment : Fragment(R.layout.fragment_onboarding) {
    private val viewModel: OnBoardingViewModel by viewModels()
    private val viewBinding by viewBinding(FragmentOnboardingBinding::bind)
    private var player: ExoPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        player = SimpleExoPlayer.Builder(requireContext()).build().apply {
            addMediaItem(MediaItem.fromUri("asset:///onboarding.mp4"))
            repeatMode = Player.REPEAT_MODE_ALL
            prepare()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.volumeControlButton.applyInsetter {
            type(statusBars = true) { margin() }
        }
        viewBinding.signUpButton.applyInsetter {
            type(navigationBars = true) { margin() }
        }
        val volume = player?.volume ?: 0f
        viewBinding.playerView.player = player
        viewBinding.viewPager.setTextPages()
        viewBinding.viewPager.previewPages()
        val textPageCount = viewBinding.viewPager.adapter?.itemCount ?: 0
        viewBinding.viewPager.attachDots(viewBinding.onboardingTextTabLayout)
        viewBinding.signInButton.setOnClickListener {
            findNavController().navigate(R.id.action_onboardingFragment_to_signInFragment)
        }
        viewBinding.signUpButton.setOnClickListener {
            findNavController().navigate(R.id.action_onboardingFragment_to_signUpFragment)
        }
        viewBinding.volumeControlButton.setOnClickListener {
            val isSelected = !viewBinding.volumeControlButton.isSelected
            viewBinding.volumeControlButton.isSelected = isSelected
            viewBinding.playerView.player?.volume = if (isSelected) volume else 0f
        }
        viewBinding.volumeControlButton.isSelected = false
        viewBinding.playerView.player?.volume = 0f

        viewBinding.viewPager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    viewModel.lastScrollTime = System.currentTimeMillis()
                }
            }
        )

        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewState.collect {
                    val currentItem = viewBinding.viewPager.currentItem
                    viewBinding.viewPager.setCurrentItem((currentItem + 1) % textPageCount, true)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        player?.play()
    }

    override fun onPause() {
        super.onPause()
        player?.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        player?.release()
    }

    private fun ViewPager2.setTextPages() {
        adapter = ListDelegationAdapter(onboardingTextAdapterDelegate()).apply {
            items = listOf(
                getString(R.string.onboarding_view_pager_text_1),
                getString(R.string.onboarding_view_pager_text_2),
                getString(R.string.onboarding_view_pager_text_3)
            )
        }
    }

    private fun ViewPager2.attachDots(tabLayout: TabLayout) {
        TabLayoutMediator(tabLayout, this) { _, _ -> }.attach()
    }

    private fun ViewPager2.previewPages() {
        offscreenPageLimit = 1

        val nextItemVisiblePx = resources.getDimension(R.dimen.viewpager_next_item_visible)
        val currentItemHorizontalMarginPx = resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin)
        val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
        val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
            page.translationX = -pageTranslationX * position
            // Next line scales the item's height. You can remove it if you don't want this effect
            page.scaleY = 1 - (0.25f * abs(position))
            // If you want a fading effect uncomment the next line:
            page.alpha = 0.25f + (1 - abs(position))
        }
        setPageTransformer(pageTransformer)

        // The ItemDecoration gives the current (centered) item horizontal margin so that
        // it doesn't occupy the whole screen width. Without it the items overlap
        val itemDecoration = HorizontalMarginItemDecoration(
            context,
            R.dimen.viewpager_current_item_horizontal_margin
        )
        addItemDecoration(itemDecoration)
    }
}