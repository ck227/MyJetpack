package com.ck.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.addCallback
import androidx.datastore.preferences.core.edit
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.ck.myjetpack.R
import com.ck.myjetpack.databinding.FragmentGuideBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

private const val pageSize = 3
private const val ARG_OBJECT = "object"
lateinit var signLayout: LinearLayout

/**
 *
 * @author ck
 * @date 2021/4/24
 */
@AndroidEntryPoint
class GuideFragment : Fragment() {

    private lateinit var demoCollectionAdapter: DemoCollectionAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentGuideBinding.inflate(inflater, container, false)
        viewLifecycleOwner.lifecycleScope.launch {
            changeFirstLoad()
        }
        demoCollectionAdapter = DemoCollectionAdapter(this)
        binding.viewPager.adapter = demoCollectionAdapter
        binding.viewPager.registerOnPageChangeCallback(MyOnPageChangeListener())
        signLayout = binding.signLayout
        initSign()
        return binding.root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        //禁用返回按钮
//        requireActivity().onBackPressedDispatcher.addCallback(this) {
//            // Handle the back button event
//        }
//    }

    //存数据
    private suspend fun changeFirstLoad() {
        context?.dataStore?.edit { settings ->
            settings[isFirstLoadKey] = false
        }
    }

    class DemoCollectionAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

        override fun getItemCount(): Int = pageSize

        override fun createFragment(position: Int): Fragment {
            val fragment = DemoObjectFragment()
            fragment.arguments = Bundle().apply {
                putInt(ARG_OBJECT, position)
            }
            return fragment
        }
    }

    class DemoObjectFragment : Fragment() {

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {
            return inflater.inflate(R.layout.fragment_guide_item, container, false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
                val ivGuide: ImageView = view.findViewById(R.id.iv_guide)

                when (getInt(ARG_OBJECT)) {
                    0 -> Glide.with(view.context)
                        .load(R.mipmap.guide1)
                        .into(ivGuide)
                    1 -> Glide.with(view.context)
                        .load(R.mipmap.guide2)
                        .into(ivGuide)
                    2 -> {
                        Glide.with(view.context)
                            .load(R.mipmap.guide3)
                            .into(ivGuide)
                        ivGuide.setOnClickListener {
                            val action =
                                GuideFragmentDirections
                                    .actionGuideFragmentToMainFragment()
                            findNavController().navigate(action)
                        }

                    }
                }
            }
        }
    }

    private fun initSign() {
        signLayout.removeAllViews()
        for (i in 0 until pageSize) {
            val signView: View =
                LayoutInflater.from(context).inflate(R.layout.guide_sign_layout, null)
            val lp = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            lp.leftMargin = 12
            signView.layoutParams = lp
            if (i == 0) {
                signView.isSelected = true
                signView.setBackgroundResource(R.mipmap.guide_selected)
            } else {
                signView.isSelected = false
                signView.setBackgroundResource(R.mipmap.guide_unselected)
            }
            signLayout.addView(signView)
        }
    }

    class MyOnPageChangeListener : ViewPager2.OnPageChangeCallback() {

        override fun onPageSelected(position: Int) {
            var i = 0
            while (i < pageSize) {
                val d: View = signLayout.getChildAt(i)
                if (i == position % pageSize) {
                    d.isSelected = true
                    d.setBackgroundResource(R.mipmap.guide_selected)
                } else {
                    d.isSelected = false
                    d.setBackgroundResource(R.mipmap.guide_unselected)
                }
                i++
            }
        }
    }
}