package com.demo.sample.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.demo.sample.R
import com.demo.sample.databinding.FragmentMainBinding

class FrgMain : Fragment() , View.OnClickListener{

    private  var _binding : FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return initOncreateView(inflater, container, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initOncreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMainBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnViewTransactions -> Toast.makeText(activity, "btnViewTransactions", Toast.LENGTH_SHORT).show()
            R.id.btnSendMoney -> Toast.makeText(activity, "btnSendMoney", Toast.LENGTH_SHORT).show()
            R.id.btnBalance -> Toast.makeText(activity, "btnBalance", Toast.LENGTH_SHORT).show()
        }
    }


}