package com.demo.code.dataStore.fragments.preferenceDataStore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.demo.code.R
import com.demo.code.databinding.FragmentPreferenceDataStoreBinding
import com.demo.code.databinding.FragmentProtoDataStoreBinding

class PreferenceDataStoreFragment : Fragment() {

    private var _binding: FragmentPreferenceDataStoreBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPreferenceDataStoreBinding.inflate(inflater, container, false)
        return binding.root
    }

}