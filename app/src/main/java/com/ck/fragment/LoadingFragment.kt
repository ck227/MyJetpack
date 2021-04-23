package com.ck.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.ck.myjetpack.databinding.FragmentLoadingBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


/**
 *
 * @author ck
 * @date 2021/4/23
 */
@AndroidEntryPoint
class LoadingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentLoadingBinding.inflate(inflater, container, false)
        navigateToMain(binding.root)
        return binding.root
    }

    private fun navigateToMain(v: View) {
        Executors.newSingleThreadScheduledExecutor().schedule({
            val action =
                LoadingFragmentDirections
                    .actionLoadingFragmentToMainFragment()
            v.findNavController().navigate(action)
        }, 2, TimeUnit.SECONDS)
    }


}



