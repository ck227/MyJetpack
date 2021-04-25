package com.ck.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.ck.myjetpack.databinding.FragmentLoadingBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

private const val USER_PREFERENCES_NAME = "setting"
private const val IS_FIRST_LOAD_KEY = "is_first_load_key"

//初始化key
val isFirstLoadKey = booleanPreferencesKey(IS_FIRST_LOAD_KEY)

//初始化DataStore
val Context.dataStore by preferencesDataStore(
    name = USER_PREFERENCES_NAME
)


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
        viewLifecycleOwner.lifecycleScope.launch {
            changeFirstLoad(binding.root)
        }
        return binding.root
    }

    //存数据
    private suspend fun changeFirstLoad(view: View) {
        val firstLoadFlow: Flow<Boolean> = requireContext().dataStore.data
            .map { preferences ->
                preferences[isFirstLoadKey] ?: true
            }
        firstLoadFlow.collect { flag ->
            if (flag) {
                navigateToGuide(view)
            } else {
                navigateToMain(view)
            }
        }
    }


    private fun navigateToGuide(v: View) {
        Executors.newSingleThreadScheduledExecutor().schedule({
            val action =
                LoadingFragmentDirections
                    .actionLoadingFragmentToGuideFragment()
            v.findNavController().navigate(action)
        }, 2, TimeUnit.SECONDS)
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



