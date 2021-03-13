package com.demo.code.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.demo.code.R
import com.demo.code.databinding.FragmentLayoutABinding

class FragmentA : Fragment() {

    private var _binding: FragmentLayoutABinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentLayoutABinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            this.FragmentANextId.setOnClickListener{
                findNavController().navigate(R.id.fragmentC)
            }
            this.FragmentABackId.setOnClickListener{

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}