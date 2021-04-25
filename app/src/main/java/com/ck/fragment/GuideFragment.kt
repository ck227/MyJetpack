package com.ck.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.preferences.core.edit
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.ck.myjetpack.databinding.FragmentGuideBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 *
 * @author ck
 * @date 2021/4/24
 */
@AndroidEntryPoint
class GuideFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentGuideBinding.inflate(inflater, container, false)
        viewLifecycleOwner.lifecycleScope.launch {
            changeFirstLoad()
        }
        return binding.root
    }

    //存数据
    private suspend fun changeFirstLoad() {
        context?.dataStore?.edit { settings ->
            settings[isFirstLoadKey] = false
        }
    }

}