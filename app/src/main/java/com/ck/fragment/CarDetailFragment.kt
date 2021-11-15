package com.ck.fragment

import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.ck.adapter.CarDetailDescAdapter
import com.ck.myjetpack.R
import com.ck.myjetpack.databinding.FragmentCarDetailBinding
import com.ck.ui.CarDetailBind
import com.ck.viewmodels.CarViewModel
import kotlinx.android.synthetic.main.base_title.view.*
import kotlin.collections.HashMap
import kotlin.collections.MutableMap
import kotlin.collections.set

class CarDetailFragment : BaseFragment() {

    private val args: CarDetailFragmentArgs by navArgs()
    private val carViewModel: CarViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCarDetailBinding.inflate(inflater, container, false)
        binding.titleLayout.iv_back.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.titleLayout.tv_title.text = "车辆详情"
        binding.originalPrice.paint.isAntiAlias = true
        binding.originalPrice.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG or Paint.ANTI_ALIAS_FLAG
        binding.viewPager.adapter = CarDetailDescAdapter(this)

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if (position == 0) {
                    binding.tab1Text.setTextColor(resources.getColor(R.color.white))
                    binding.tab2Text.setTextColor(resources.getColor(R.color.white_transparent))
                    binding.tab1Text.setBackgroundResource(R.drawable.car_detail_selected_bg)
                    binding.tab2Text.setBackgroundResource(R.drawable.car_detail_unselected_bg)
                } else {
                    binding.tab1Text.setTextColor(resources.getColor(R.color.white_transparent))
                    binding.tab2Text.setTextColor(resources.getColor(R.color.white))
                    binding.tab1Text.setBackgroundResource(R.drawable.car_detail_unselected_bg)
                    binding.tab2Text.setBackgroundResource(R.drawable.car_detail_selected_bg)
                }
            }
        })

        binding.setCarDetailClick0 {
            binding.viewPager.setCurrentItem(0, true)
        }

        binding.setCarDetailClick1 {
            binding.viewPager.setCurrentItem(1, true)
        }

        getData(binding)
        return binding.root
    }

    private fun getData(binding: FragmentCarDetailBinding) {

        carViewModel.carDetail.observe(viewLifecycleOwner) { carDetailResponse ->
            with(binding) {
                //设置上面的车辆信息
                carDetailBind = CarDetailBind(carDetailResponse.data)
                executePendingBindings()
            }
        }

        val map: MutableMap<String, String> = HashMap()
        map["carId"] = args.carId
        carViewModel.getCarDetail(map)
    }


}