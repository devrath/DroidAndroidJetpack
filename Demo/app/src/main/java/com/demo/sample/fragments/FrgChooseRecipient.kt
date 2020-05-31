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
import kotlinx.android.synthetic.main.fragment_choose_recipient.*

class FrgChooseRecipient :Fragment(),View.OnClickListener {


    private var _binding : FragmentChooseRecipientBinding? = null
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
        initOnViewCreated(view,savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initOnCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentChooseRecipientBinding.inflate(inflater,container,false)
        return binding.root
    }

    private fun initOnViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = Navigation.findNavController(view)
        setOnClickListener()
    }

    private fun setOnClickListener() {
        recipientNext.setOnClickListener(this)
        recipientCancel.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.recipientNext -> navController.navigate(R.id.action_frgChooseRecipient2_to_frgConfirmation)
            R.id.recipientCancel -> activity?.onBackPressed()
        }
    }


}