package com.demo.sample.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.demo.sample.R
import com.demo.sample.databinding.FragmentChooseRecipientBinding
import com.demo.sample.databinding.FragmentSpecifyAmountBinding
import kotlinx.android.synthetic.main.fragment_specify_amount.view.*

class FrgSpecifyAmount : Fragment() , View.OnClickListener {


    private var _binding : FragmentSpecifyAmountBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return initOnCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewCreated(view, savedInstanceState)
    }

    private fun initViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = Navigation.findNavController(view)
        setOnClickListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnNext -> navController.navigate(R.id.action_frgSpecifyAmount_to_frgChooseRecipient2)
            R.id.btnCancel -> activity?.onBackPressed()
        }
    }

    private fun initOnCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentSpecifyAmountBinding.inflate(inflater,container,false)
        return binding.root
    }

    private fun setOnClickListeners() {
       binding.btnNext.setOnClickListener(this)
       binding.btnCancel.setOnClickListener(this)
    }


}