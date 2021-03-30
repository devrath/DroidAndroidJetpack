package com.demo.code.paging.horizontalPaging.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.code.R
import com.demo.code.databinding.HorizontalPagingFragmentBinding
import com.demo.code.databinding.MutableLiveDataFragmentBinding
import com.demo.code.paging.adapters.MainListAdapter
import com.demo.code.paging.factory.MainViewModelFactory
import com.demo.code.paging.horizontalPaging.vm.HorizontalPagingViewModel
import com.demo.code.paging.network.APIService
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HorizontalPagingFragment : Fragment() {

    private var _binding: HorizontalPagingFragmentBinding? = null
    private val binding get() = _binding!!

    lateinit var viewModel: HorizontalPagingViewModel
    lateinit var mainListAdapter: MainListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HorizontalPagingFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //viewModel = ViewModelProvider(this).get(HorizontalPagingViewModel::class.java)
        setupViewModel()
        setupList()
        setupView()
    }


    private fun setupView() {
        lifecycleScope.launch {
            viewModel.listData.collect {
                mainListAdapter.submitData(it)
            }
        }
    }

    private fun setupList() {
        mainListAdapter = MainListAdapter()
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mainListAdapter
        }
    }

    private fun setupViewModel() {
        viewModel =
            ViewModelProvider(
                this,
                MainViewModelFactory(APIService.getApiService())
            )[HorizontalPagingViewModel::class.java]
    }


}