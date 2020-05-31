package com.demo.sample.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.demo.sample.R
import com.demo.sample.databinding.FragmentChooseRecipientBinding
import com.demo.sample.utils.Utils
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_choose_recipient.*
import kotlinx.android.synthetic.main.fragment_specify_amount.*

class FrgChooseRecipient :Fragment(),View.OnClickListener {


    private var _binding : FragmentChooseRecipientBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private var amount : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        amount = arguments?.getInt("amount")!!
    }

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
            R.id.recipientNext -> {
                if(!TextUtils.isEmpty(reciepentNameId.text.toString())){
                    val mName = reciepentNameId.text.toString()
                    val bundle = bundleOf(Pair("amount", amount), Pair("name", mName))
                    navController.navigate(R.id.action_frgChooseRecipient2_to_frgConfirmation,bundle)
                }else{
                    activity?.let { Utils.hideKeyboard(it, recipientNext) }
                    Utils.snack(btnNext,"Please enter recipient name")
                }

            }
            R.id.recipientCancel -> activity?.onBackPressed()
        }
    }

}