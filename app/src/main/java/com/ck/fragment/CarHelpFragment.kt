package com.ck.fragment

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.ck.myjetpack.databinding.FragmentCarHelpBinding
import com.ck.viewmodels.CarHelpViewModel
import kotlinx.android.synthetic.main.base_title.view.*

class CarHelpFragment : BaseFragment() {

    private val carHelpViewModel: CarHelpViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCarHelpBinding.inflate(inflater, container, false)
        binding.titleLayout.iv_back.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.titleLayout.tv_title.text = "车辆托管"
        binding.setCklistener {
            if (checkValue(binding)) {
                carHelpViewModel.response.observe(viewLifecycleOwner) { baseResponse ->
                    Toast.makeText(context, baseResponse.message, Toast.LENGTH_SHORT).show()
                    if ("0" == baseResponse.status) {
                        findNavController().navigateUp()
                    }
                }

                val map: MutableMap<String, String> = HashMap()
                map["userId"] = "99"
                map["userName"] = binding.etName.text.toString()
                map["phone"] = binding.etPhone.text.toString()
                map["plateNumber"] = binding.etCarNo.text.toString()
                map["carBrand"] = binding.etCarBrand.text.toString()
                map["carModel"] = binding.etCarModel.text.toString()
                map["nakedPrice"] = binding.etPrice.text.toString()
                map["remarks"] = binding.etRemark.text.toString()

                carHelpViewModel.submitCarHelp(map)
            }
        }

        return binding.root
    }

    private fun checkValue(binding: FragmentCarHelpBinding): Boolean {
        if (TextUtils.isEmpty(binding.etName.text.toString())) {
            Toast.makeText(context, "请输入姓名", Toast.LENGTH_SHORT).show()
            return false
        }
        val phone = binding.etPhone.text.toString()
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(context, "请输入联系方式", Toast.LENGTH_SHORT).show()
            return false
        }
        if (!phone.startsWith("1") || phone.length != 11) {
            Toast.makeText(context, "手机号格式错误", Toast.LENGTH_SHORT).show()
            return false
        }
        if (TextUtils.isEmpty(binding.etCarNo.text.toString())) {
            Toast.makeText(context, "请输入车牌号", Toast.LENGTH_SHORT).show()
            return false
        }
        if (TextUtils.isEmpty(binding.etCarBrand.text.toString())) {
            Toast.makeText(context, "请输入车辆品牌", Toast.LENGTH_SHORT).show()
            return false
        }
        if (TextUtils.isEmpty(binding.etCarModel.text.toString())) {
            Toast.makeText(context, "请输入车辆型号", Toast.LENGTH_SHORT).show()
            return false
        }
        if (TextUtils.isEmpty(binding.etPrice.text.toString())) {
            Toast.makeText(context, "请输入裸车价", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

}