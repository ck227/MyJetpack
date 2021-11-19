package com.ck.fragment

import android.content.Context
import android.icu.text.StringPrepParseException
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ck.myjetpack.databinding.FragmentLoadingBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

private const val USER_PREFERENCES_NAME = "setting"
private const val IS_FIRST_LOAD_KEY = "is_first_load_key"
private const val USER_KEY = "user_key"

//初始化key
val isFirstLoadKey = booleanPreferencesKey(IS_FIRST_LOAD_KEY)
val userKey = stringPreferencesKey(USER_KEY)


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
            delay(2000)
            changeFirstLoad()
        }
        return binding.root
    }

    //取数据
    private suspend fun changeFirstLoad() {
        val firstLoadFlow: Flow<Boolean> = requireContext().dataStore.data
            .map { preferences ->
                preferences[isFirstLoadKey] ?: true
            }
        firstLoadFlow.collect { flag ->
            navigateToNext(flag)
        }
    }

    private fun navigateToNext(boolean: Boolean) {
        if (boolean) {
            val action =
                LoadingFragmentDirections
                    .actionLoadingFragmentToGuideFragment()
            findNavController().navigate(action)
        } else {
            val action =
                LoadingFragmentDirections
                    .actionLoadingFragmentToMainFragment()
            findNavController().navigate(action)
        }
    }
}



