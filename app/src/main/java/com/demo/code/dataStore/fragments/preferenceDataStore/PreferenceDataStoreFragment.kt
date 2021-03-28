package com.demo.code.dataStore.fragments.preferenceDataStore

import android.content.Context
import android.os.Bundle
import android.os.UserManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.asLiveData
import com.demo.code.R
import com.demo.code.dataStore.util.DataManager
import com.demo.code.databinding.FragmentPreferenceDataStoreBinding
import com.demo.code.databinding.FragmentProtoDataStoreBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PreferenceDataStoreFragment : Fragment() {

    private var _binding: FragmentPreferenceDataStoreBinding? = null
    private val binding get() = _binding!!

    lateinit var dataManager: DataManager
    var name = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPreferenceDataStoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDataStore()
        observeData()
        saveData()
    }

    private fun observeData() {
        // of UserManager class
        this.dataManager.dataAsFlow.asLiveData().observe(requireActivity()) {
            binding.displayTextId.text = it.toString()
        }
    }

    private fun saveData() {
        binding.apply {
            saveDataId.setOnClickListener {
                val dataToSave = binding.editTextTextPersonName.text.toString()
                GlobalScope.launch { dataManager.storeData(dataToSave) }
            }
        }
    }

    private fun initDataStore() {
        // Get reference to our userManager class
        dataManager = DataManager(requireContext())
    }

}