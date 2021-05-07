package com.ck.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ck.data.HomeBean
import com.ck.myjetpack.databinding.FragmentBannerBinding
import com.ck.myjetpack.databinding.FragmentGuideBinding
import dagger.hilt.android.AndroidEntryPoint


/**
 *
 * @author ck
 * @date 2021/5/7
 */
@AndroidEntryPoint
class BannerFragment : BaseFragment() {

    companion object {
        fun newInstance(banner: HomeBean): BannerFragment {
            val args = Bundle()
            args.putParcelable("banner", banner);
            val fragment = BannerFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentBannerBinding.inflate(inflater, container, false)
        binding.apply {
            homeBean = arguments?.getParcelable("banner")
            executePendingBindings()
        }
        return binding.root
    }

}