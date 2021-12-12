package com.ck.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.text.toUpperCase
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ck.data.viewmodel.UpdateUserViewModel
import com.ck.myjetpack.R
import com.ck.myjetpack.databinding.FragmentReChargeBinding
import com.tencent.mm.opensdk.modelpay.PayReq
import com.tencent.mm.opensdk.openapi.WXAPIFactory
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.internal.and
import java.lang.Exception
import java.security.MessageDigest

@AndroidEntryPoint
class ReChargeFragment : BaseFragment() {

    private val wxAppId = "wxfa1beb51cb4f5bd9"

    private val args: ReChargeFragmentArgs by navArgs()

    private var payType = 2

    private lateinit var updateUserViewModel: UpdateUserViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentReChargeBinding.inflate(inflater, container, false)
        binding.titleLayout.setBackListener {
            findNavController().navigateUp()
        }
        binding.titleLayout.title.text = "在线充值"
        binding.relWechatPay.setOnClickListener {
            isWxPay(binding, true)
        }
        binding.relAliPay.setOnClickListener {
            isWxPay(binding, false)
        }

        binding.tvSubmit.setOnClickListener {
            val map: MutableMap<String, String> = HashMap()
            map["userId"] = args.userId
            map["rechargePrice"] = "0.01"
            map["type"] = "app"
            updateUserViewModel.getWxPayInfo(map)
        }
        updateUserViewModel = ViewModelProvider(this).get(UpdateUserViewModel::class.java)
        updateUserViewModel.wxPayInfoResponse.observe(viewLifecycleOwner, {
            val wxApi = WXAPIFactory.createWXAPI(context, wxAppId)
            wxApi.registerApp(wxAppId)
            val request = PayReq()
            request.appId = it.appId
            request.partnerId = it.mchid
            request.prepayId = it.prepay_id
            request.packageValue = "Sign=WXPay"
            request.nonceStr = it.nonceStr
            request.timeStamp = it.timeStamp
            request.sign = reSign(
                it.mchid,
                it.prepay_id,
                it.nonceStr,
                "Sign=WXPay",
                it.timeStamp,
                "xinweifengchuxin2019103012345678"
            )
            wxApi.sendReq(request)
//            findNavController().navigateUp()
        })


        return binding.root
    }

    private fun isWxPay(binding: FragmentReChargeBinding, isWxPay: Boolean) {
        payType = if (isWxPay) 1 else 2
        binding.ivWechatPay.setImageResource(if (isWxPay) R.mipmap.online_pay_selected else R.mipmap.online_pay_unselected)
        binding.ivAliPay.setImageResource(if (isWxPay) R.mipmap.online_pay_unselected else R.mipmap.online_pay_selected)
    }

    //接口返回的签名不对，只好本地重新签了
    private fun reSign(
        partnerId: String,
        prepayId: String,
        nonceStr: String,
        packageValue: String,
        timeStamp: String,
        key: String
    ): String {
        val StringA =
            "appid=$wxAppId&noncestr=$nonceStr&package=$packageValue&partnerid=$partnerId&prepayid=$prepayId&timestamp=$timeStamp&key=$key"
        return getMessageDigest(StringA.toByteArray()).toString().uppercase()
    }

    fun getMessageDigest(buffer: ByteArray?): String? {
        val hexDigits = charArrayOf(
            '0',
            '1',
            '2',
            '3',
            '4',
            '5',
            '6',
            '7',
            '8',
            '9',
            'a',
            'b',
            'c',
            'd',
            'e',
            'f'
        )
        return try {
            val mdTemp = MessageDigest.getInstance("MD5")
            mdTemp.update(buffer)
            val md = mdTemp.digest()
            val j = md.size
            val str = CharArray(j * 2)
            var k = 0
            for (i in 0 until j) {
                val byte0 = md[i]
                val v = byte0.toInt() and 0xFF
                str[k++] = hexDigits[v.ushr(4)]
                str[k++] = hexDigits[byte0 and 0xf]
            }
            String(str)
        } catch (e: Exception) {
            null
        }
    }


}