package com.ck.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.ck.myjetpack.R
import com.ck.myjetpack.databinding.FragmentMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


/**
 *
 * @author ck
 * @date 2020/12/1
 */
class MainFragment : Fragment() {

    private lateinit var navController: NavController
    private lateinit var bottomNav: BottomNavigationView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMainBinding.inflate(inflater, container, false)
        val navHostFragment = childFragmentManager.findFragmentById(
            R.id.nav_host_container
        ) as NavHostFragment
        navController = navHostFragment.navController
        bottomNav = binding.bottomNav
        bottomNav.setupWithNavController(navController)
        return binding.root
    }

    fun changeTab() {
        bottomNav.selectedItemId = R.id.home1
    }

    fun search() {
        findNavController().navigate(MainFragmentDirections.actionMainFragmentToSearchFragment())
    }

    fun goMessage() {
        findNavController().navigate(MainFragmentDirections.actionMainFragmentToMessageFragment())
    }

    fun openCustomerService() {
        findNavController().navigate(MainFragmentDirections.actionMainFragmentToCustomerFragment())
    }

    fun openVipCenter() {
        findNavController().navigate(MainFragmentDirections.actionMainFragmentToVipCenterFragment())
    }


}