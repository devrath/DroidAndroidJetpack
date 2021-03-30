package com.demo.code.paging.horizontalPaging.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.demo.code.R
import com.demo.code.databinding.HorizontalPagingFragmentBinding
import com.demo.code.databinding.MutableLiveDataFragmentBinding
import com.demo.code.paging.horizontalPaging.vm.HorizontalPagingViewModel

class HorizontalPagingFragment : Fragment() {

    private var _binding: HorizontalPagingFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: HorizontalPagingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HorizontalPagingFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HorizontalPagingViewModel::class.java)

    }

}