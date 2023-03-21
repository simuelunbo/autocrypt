package com.simuel.autocrypt

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.simuel.autocrypt.base.BaseFragment
import com.simuel.autocrypt.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(R.layout.fragment_splash) {
    private val viewModel: MainViewModel by activityViewModels()
    override fun init() {
        binding.vm = viewModel
        viewModelCallback()
    }

    private fun viewModelCallback() {
        viewModel.progress.observe(viewLifecycleOwner) {
            if (it == 100) {
                findNavController().navigate(R.id.action_splashFragment_to_mapFragment)
            }
        }
    }

}