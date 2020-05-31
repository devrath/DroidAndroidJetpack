package com.demo.sample.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.demo.sample.R
import com.demo.sample.databinding.FragmentMainBinding

class FrgMain : Fragment() , View.OnClickListener{

    private  var _binding : FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController : NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return initOncreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initOnViewCreated(view,savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnViewTransactions -> navController.navigate(R.id.action_frgMain2_to_frgViewTransaction2)
            R.id.btnSendMoney -> navController.navigate(R.id.action_frgMain2_to_frgSpecifyAmount)
            R.id.btnBalance -> navController.navigate(R.id.action_frgMain2_to_frgBalance2)
        }
    }

    private fun initOncreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMainBinding.inflate(inflater,container,false)
        return binding.root
    }

    private fun initOnViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = Navigation.findNavController(view)
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding.btnBalance.setOnClickListener(this)
        binding.btnSendMoney.setOnClickListener(this)
        binding.btnViewTransactions.setOnClickListener(this)
    }




}