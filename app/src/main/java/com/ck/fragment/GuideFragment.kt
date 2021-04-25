package com.ck.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.work.impl.utils.Preferences
import com.ck.myjetpack.databinding.FragmentHome0Binding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


private const val USER_PREFERENCES_NAME = "setting"
private const val IS_FIRST_LOAD_KEY = "is_first_load_key"

/**
 *
 * @author ck
 * @date 2021/4/24
 */
@AndroidEntryPoint
class GuideFragment : Fragment() {

    //初始化DataStore
    private val Context.dataStore by preferencesDataStore(
        name = USER_PREFERENCES_NAME
    )

    //初始化key
    private val isFirstLoadKey = booleanPreferencesKey(IS_FIRST_LOAD_KEY)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHome0Binding.inflate(inflater, container, false)
        viewLifecycleOwner.lifecycleScope.launch {
            changeFirstLoad()
        }
        return binding.root
    }

    //存数据
    private suspend fun changeFirstLoad() {
        requireContext().dataStore.edit { settings ->
            settings[isFirstLoadKey] = false
        }
    }

    //取数据
//    val EXAMPLE_COUNTER = intPreferencesKey("example_counter")
//    val exampleCounterFlow: Flow<Int> = context.dataStore.data
//        .map { preferences ->
//            // No type safety.
//            preferences[EXAMPLE_COUNTER] ?: 0
//        }

}