package com.demo.code.dataStore.fragments.protoDataStore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.demo.code.R
import com.demo.code.databinding.FragmentProtoDataStoreBinding
import com.demo.code.databinding.FragmentSelectionDataStoreBinding

class ProtoDataStoreFragment : Fragment() {

    private var _binding: FragmentProtoDataStoreBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProtoDataStoreBinding.inflate(inflater, container, false)
        return binding.root
    }

}