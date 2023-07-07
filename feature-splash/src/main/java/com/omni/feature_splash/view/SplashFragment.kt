package com.omni.feature_splash.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.omni.navigation.R
import com.omni.feature_splash.databinding.FragmentSplashBinding
import com.omni.feature_splash.viewmodel.SplashViewModel
import kotlinx.coroutines.delay
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class SplashFragment : Fragment() {
    private lateinit var _binding: FragmentSplashBinding
    private val viewModel: SplashViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        FragmentSplashBinding.inflate(inflater, container, false).let {
            _binding = it

            navigateToNextScreen()
        }.also {
            return _binding.root
        }
    }

    private fun navigateToNextScreen() {
        lifecycleScope.launchWhenStarted {
            viewModel.getGenere().collect { genere ->
                delay(2000)
                try {
                    if (genere.isNullOrEmpty())
                        findNavController().navigate(R.id.action_splashFragment_to_generesFragment)
                    else
                        findNavController().navigate(R.id.action_splashFragment_to_gamesListFragment)
                } catch (e: IllegalArgumentException) {
                    Timber.e(e)
                }

            }
        }
    }
}