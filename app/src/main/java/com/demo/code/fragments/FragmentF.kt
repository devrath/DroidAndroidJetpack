package com.demo.code.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.demo.code.databinding.FragmentLayoutFBinding
import com.demo.code.utils.extensions.toast

class FragmentF : Fragment() {

    private var _binding: FragmentLayoutFBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLayoutFBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            this.FragmentDNextId.setOnClickListener{
                activity?.let {
                    toast("Last Fragment",false,it)
                }
            }
            this.FragmentDBackId.setOnClickListener{

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}