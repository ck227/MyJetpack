package com.ck.fragment

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.NavHostFragment
import androidx.viewpager2.widget.ViewPager2
import com.ck.adapter.CarListAdapter
import com.ck.adapter.HomeBannerAdapter
import com.ck.adapter.HomeDiscountAdapter
import com.ck.adapter.HomeNewsAdapter
import com.ck.data.HomeBean
import com.ck.myjetpack.R
import com.ck.myjetpack.databinding.FragmentHome0Binding
import com.ck.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.collections.ArrayList


/**
 * 首页
 * @author ck
 * @date 2020/12/1
 */
@AndroidEntryPoint
class HomeFragment0 : BaseFragment() {

    private val viewModel: HomeViewModel by activityViewModels()
    private lateinit var viewPager: ViewPager2
    private lateinit var dotsLayout: LinearLayout
    private lateinit var slidingImageDots: ArrayList<ImageView>
    private lateinit var list: ArrayList<HomeBean>
    private lateinit var homeBannerAdapter: HomeBannerAdapter
    private var currentPage: Int = 0
    private lateinit var root: View

    //限时折扣
    private lateinit var homeDiscountAdapter: HomeDiscountAdapter

    //热门推荐
    private lateinit var carListAdapter: CarListAdapter

    //资讯中心
    private lateinit var homeNewsAdapter: HomeNewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHome0Binding.inflate(inflater, container, false)
        root = binding.root
        //初始化轮播控件
        viewPager = binding.banner
        dotsLayout = binding.signLayout
        list = ArrayList()
        homeBannerAdapter = HomeBannerAdapter(list, this)
        binding.banner.adapter = homeBannerAdapter
        //初始化限时折扣控件
        homeDiscountAdapter = HomeDiscountAdapter()
        binding.discountRecyclerView.adapter = homeDiscountAdapter
        //热门推荐
        carListAdapter = CarListAdapter()
        binding.hotRecyclerView.adapter = carListAdapter
        //资讯中心
        homeNewsAdapter = HomeNewsAdapter()
        binding.newsRecyclerView.adapter = homeNewsAdapter



        getBanner()
        getCarList()
        getSuggest()
        getNews()

        binding.search.setOnClickListener {
            if (parentFragment is NavHostFragment) {
                if ((parentFragment as NavHostFragment).parentFragment is MainFragment) {
                    ((parentFragment as NavHostFragment).parentFragment as MainFragment).search()
                }
            }
        }
        binding.message.setOnClickListener {
            if (parentFragment is NavHostFragment) {
                if ((parentFragment as NavHostFragment).parentFragment is MainFragment) {
                    ((parentFragment as NavHostFragment).parentFragment as MainFragment).goMessage()
                }
            }
        }

        binding.homeIcon1.setOnClickListener {
            if (parentFragment is NavHostFragment) {
                if ((parentFragment as NavHostFragment).parentFragment is MainFragment) {
                    ((parentFragment as NavHostFragment).parentFragment as MainFragment).changeTab()
                }
            }
        }

        binding.homeIcon2.setOnClickListener {
            if (parentFragment is NavHostFragment) {
                if ((parentFragment as NavHostFragment).parentFragment is MainFragment) {
                    ((parentFragment as NavHostFragment).parentFragment as MainFragment).openCustomerService()
                }
            }
        }

        binding.homeIcon3.setOnClickListener {
            if (parentFragment is NavHostFragment) {
                if ((parentFragment as NavHostFragment).parentFragment is MainFragment) {
                    ((parentFragment as NavHostFragment).parentFragment as MainFragment).openVipCenter()
                }
            }
        }

        binding.homeIcon4.setOnClickListener {
            if (parentFragment is NavHostFragment) {
                if ((parentFragment as NavHostFragment).parentFragment is MainFragment) {
                    ((parentFragment as NavHostFragment).parentFragment as MainFragment).openCarHelp()
                }
            }
        }

        binding.setDisCountListener {
            if (parentFragment is NavHostFragment) {
                if ((parentFragment as NavHostFragment).parentFragment is MainFragment) {
                    ((parentFragment as NavHostFragment).parentFragment as MainFragment).openDiscount(
                        "限时折扣", false
                    )
                }
            }
        }

        binding.setHotListener {
            if (parentFragment is NavHostFragment) {
                if ((parentFragment as NavHostFragment).parentFragment is MainFragment) {
                    ((parentFragment as NavHostFragment).parentFragment as MainFragment).openDiscount(
                        "热门推荐", true
                    )
                }
            }
        }

        binding.setNewsListener {
            if (parentFragment is NavHostFragment) {
                if ((parentFragment as NavHostFragment).parentFragment is MainFragment) {
                    ((parentFragment as NavHostFragment).parentFragment as MainFragment).openNews()
                }
            }
        }

        return binding.root
    }

    /**
     * 获取banner
     */
    private fun getBanner() {
        viewModel.banners.observe(viewLifecycleOwner) { homeResponse ->
            list.clear()
            list.addAll(homeResponse.data)
            homeBannerAdapter.notifyItemRangeInserted(0, homeResponse.data.size)
            viewPager.registerOnPageChangeCallback(slidingCallback)
            initDots(list.size)
        }
    }

    /**
     * 限时折扣
     */
    private fun getCarList() {
        viewModel.discountCars.observe(viewLifecycleOwner) { carResponse ->
            homeDiscountAdapter.submitList(carResponse.data)
        }
    }

    /**
     * 热门推荐
     */
    private fun getSuggest() {
        viewModel.suggestCars.observe(viewLifecycleOwner) { carResponse ->
            carListAdapter.submitList(carResponse.data)

        }
    }

    /**
     * 资讯中心
     */
    private fun getNews() {
        viewModel.homeNews.observe(viewLifecycleOwner) { newsResponse ->
            homeNewsAdapter.submitList(newsResponse.data)
        }
    }

    private fun initDots(count: Int) {
        val params =
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        params.setMargins(8, 0, 8, 0)
        slidingImageDots = ArrayList(count)
        for (i in 0 until count) {
            slidingImageDots.add(ImageView(context))
            slidingImageDots[i].setImageDrawable(
                context?.let {
                    ContextCompat.getDrawable(
                        it,
                        R.mipmap.sign_unselected
                    )
                }
            )
            dotsLayout.addView(slidingImageDots[i], params)
        }
        slidingImageDots[0].setImageDrawable(
            context?.let {
                ContextCompat.getDrawable(
                    it,
                    R.mipmap.sign_selected
                )
            }
        )
        val handler = Handler()
        val update = Runnable {
            if (currentPage == list.size) {
                currentPage = 0
            }
            viewPager.setCurrentItem(currentPage++, true)
        }
        Timer().schedule(object : TimerTask() {
            override fun run() {
                handler.post(update)
            }
        }, 0, 3500)
    }

    private val slidingCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            for (i in 0 until list.size) {
                slidingImageDots[i].setImageDrawable(
                    context?.let {
                        ContextCompat.getDrawable(
                            it,
                            R.mipmap.sign_unselected
                        )
                    }
                )
            }

            slidingImageDots[position].setImageDrawable(
                context?.let {
                    ContextCompat.getDrawable(
                        it,
                        R.mipmap.sign_selected
                    )
                }
            )
        }
    }

}