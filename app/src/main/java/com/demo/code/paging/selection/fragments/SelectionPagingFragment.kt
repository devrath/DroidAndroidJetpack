package com.demo.code.paging.selection.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.demo.code.R
import com.demo.code.databinding.SelectionPagingFragmentBinding
import com.demo.code.liveData.selection.fragments.SelectionLiveDataFragment
import com.demo.code.paging.selection.vm.SelectionPagingViewModel

class SelectionPagingFragment : Fragment(), View.OnClickListener  {

    companion object {
        fun newInstance() = SelectionLiveDataFragment()
    }

    private var _binding: SelectionPagingFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: SelectionPagingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SelectionPagingFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(SelectionPagingViewModel::class.java)
        setClickListener()
    }

    private fun setClickListener() {
        binding.pagingThreeId.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.pagingThreeId -> {
                findNavController().navigate(R.id.horizontalPagingFragment)
                }
        }
    }

}