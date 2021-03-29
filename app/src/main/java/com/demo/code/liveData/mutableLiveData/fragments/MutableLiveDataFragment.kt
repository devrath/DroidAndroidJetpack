package com.demo.code.liveData.mutableLiveData.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.demo.code.databinding.MutableLiveDataFragmentBinding
import com.demo.code.liveData.mutableLiveData.vm.MutableLiveDataViewModel

class MutableLiveDataFragment : Fragment() {

    companion object {
        fun newInstance() = MutableLiveDataFragment()
    }

    private var _binding: MutableLiveDataFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MutableLiveDataViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MutableLiveDataFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MutableLiveDataViewModel::class.java)

    }

}