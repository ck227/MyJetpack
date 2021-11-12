package com.ck.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import com.ck.myjetpack.databinding.FragmentCarDetailDesc0Binding
import com.ck.ui.CarDetailDescBind
import com.ck.viewmodels.CarViewModel

class CarDetailDesFragment0 : BaseFragment() {

    private val carViewModel: CarViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCarDetailDesc0Binding.inflate(inflater, container, false)

        carViewModel.carDetail.observe(viewLifecycleOwner) { carDetail ->
            with(binding) {
                carDetailBind = CarDetailDescBind(carDetail.data)
                executePendingBindings()
            }
        }
        return binding.root
    }

}