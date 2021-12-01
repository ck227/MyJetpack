package com.ck.fragment

import android.graphics.Paint
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.ck.adapter.CarDetailBannerAdapter
import com.ck.adapter.CarDetailDescAdapter
import com.ck.data.CarDetailBean
import com.ck.myjetpack.R
import com.ck.myjetpack.User
import com.ck.myjetpack.databinding.FragmentCarDetailBinding
import com.ck.ui.CarDetailBind
import com.ck.util.UserViewModel
import com.ck.viewmodels.CarViewModel
import kotlinx.android.synthetic.main.base_title.view.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.set

class CarDetailFragment : BaseFragment() {

    private val args: CarDetailFragmentArgs by navArgs()
    private val carViewModel: CarViewModel by activityViewModels()
    private lateinit var userViewModel: UserViewModel
    private lateinit var user: User
    private var isCollection = 0


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCarDetailBinding.inflate(inflater, container, false)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
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

        binding.setCarDetailCustomerService {
            findNavController().navigate(CarDetailFragmentDirections.actionCarDetailFragmentToCustomerFragment())
        }

        binding.ivCollect.setOnClickListener {
            if (user.id.isEmpty()) {
                Toast.makeText(context, "请登录", Toast.LENGTH_SHORT).show()
            } else {
                val map: MutableMap<String, String> = HashMap()
                map["carId"] = args.carId
                map["userId"] = user.id
                map["type"] = if (isCollection == 0) "1" else "2"
                carViewModel.updateCollect(map)
            }
        }
        carViewModel.updateCollectResponse.observe(viewLifecycleOwner, {
            if ("0" == it.status) {
                isCollection = if (isCollection == 0) 1 else 0
                binding.ivCollect.setImageResource(if (isCollection == 0) R.mipmap.car_detail_uncollected else R.mipmap.car_detail_collected)
            }
        })

        binding.setCarDetailOrder {

        }

        userViewModel.user.observe(viewLifecycleOwner, {
            user = it
            val map: MutableMap<String, String> = HashMap()
            map["carId"] = args.carId
            if (it.id.isNotEmpty()) {
                map["userId"] = it.id
            }
            getData(binding, map)
        })



        return binding.root
    }

    private lateinit var timer: Timer
    private lateinit var handler: Handler
    private var currentPage: Int = 0
    private lateinit var slidingImageDots: ArrayList<ImageView>

    private fun getData(binding: FragmentCarDetailBinding, map: MutableMap<String, String>) {

        carViewModel.carDetail.observe(viewLifecycleOwner) { carDetailResponse ->
            binding.banner.adapter = CarDetailBannerAdapter(carDetailResponse.data.imgList)
            binding.banner.registerOnPageChangeCallback(slidingCallback)
            initDots(binding, carDetailResponse.data.imgList.size)
            with(binding) {
                isCollection = carDetailResponse.data.isCollection
                carDetailBind = CarDetailBind(carDetailResponse.data)
                executePendingBindings()
            }
        }
        carViewModel.getCarDetail(map)
    }

    private fun initDots(binding: FragmentCarDetailBinding, count: Int) {
        val params =
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        params.setMargins(8, 0, 8, 0)
        slidingImageDots = ArrayList(count)
        binding.signLayout.removeAllViews()
        for (i in 0 until count) {
            slidingImageDots.add(ImageView(context))
            slidingImageDots[i].setImageDrawable(
                context?.let {
                    ContextCompat.getDrawable(
                        it,
                        if (i == 0) R.drawable.car_detail_banner_selected else R.drawable.car_detail_banner_unselected
                    )
                }
            )
            binding.signLayout.addView(slidingImageDots[i], params)
        }
        val update = Runnable {
            if (currentPage == count) {
                currentPage = 0
            }
            binding.banner.setCurrentItem(currentPage++, true)
        }
        handler = Handler()
        var timerTask = object : TimerTask() {
            override fun run() {
                handler.post(update)
            }
        }
        timer = Timer()
        timer.schedule(timerTask, 0, 2000)
    }

    override fun onStop() {
        super.onStop()
        timer.cancel()
    }

    private val slidingCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            for (i in 0 until slidingImageDots.size) {
                slidingImageDots[i].setImageDrawable(
                    context?.let {
                        ContextCompat.getDrawable(
                            it,
                            if (position == i) R.drawable.car_detail_banner_selected else R.drawable.car_detail_banner_unselected
                        )
                    }
                )
            }
        }
    }


}