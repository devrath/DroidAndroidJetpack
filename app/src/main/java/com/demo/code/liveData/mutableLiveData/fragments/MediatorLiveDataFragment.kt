package com.demo.code.liveData.mutableLiveData.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.demo.code.databinding.MediatorLiveDataFragmentBinding
import com.demo.code.liveData.mutableLiveData.vm.MediatorLiveDataViewModel

class MediatorLiveDataFragment : Fragment() {

    companion object {
        fun newInstance() = MediatorLiveDataFragment()
    }

    private var _binding: MediatorLiveDataFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MediatorLiveDataViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MediatorLiveDataFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MediatorLiveDataViewModel::class.java)

    }

}