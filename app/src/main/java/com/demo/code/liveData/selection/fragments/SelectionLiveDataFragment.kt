package com.demo.code.liveData.selection.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.demo.code.R
import com.demo.code.databinding.FragmentSelectionDataStoreBinding
import com.demo.code.databinding.SelectionLiveDataFragmentBinding
import com.demo.code.liveData.selection.vm.SelectionLiveDataViewModel

class SelectionLiveDataFragment : Fragment(), View.OnClickListener  {

    companion object {
        fun newInstance() = SelectionLiveDataFragment()
    }

    private var _binding: SelectionLiveDataFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: SelectionLiveDataViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SelectionLiveDataFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(SelectionLiveDataViewModel::class.java)
        setClickListener()
    }

    private fun setClickListener() {
        binding.mutableLiveDataId.setOnClickListener(this)
        binding.mediatorLiveDataId.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.mutableLiveDataId -> {
                findNavController().navigate(R.id.mutableLiveDataFragment)
                }
            R.id.mediatorLiveDataId -> {
                findNavController().navigate(R.id.mediatorLiveDataFragment)
            }
        }
    }

}