package com.demo.code.dataStore.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.demo.code.R
import com.demo.code.databinding.ActivityDataStoreBinding
import com.demo.code.databinding.FragmentSelectionDataStoreBinding
import com.demo.code.navigation.activities.NavigationDrawerActivity
import com.demo.code.navigation.activities.TwoFragmentContainerActivity
import com.demo.extensions.intent.openActivity


class SelectionDataStoreFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentSelectionDataStoreBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSelectionDataStoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListener()
    }

    private fun setClickListener() {
        binding.preferencesDsId.setOnClickListener(this)
        binding.protoDsId.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.preferencesDsId -> findNavController().navigate(R.id.preferenceDataStoreFragment)
            R.id.protoDsId -> findNavController().navigate(R.id.protoDataStoreFragment)
        }
    }

}