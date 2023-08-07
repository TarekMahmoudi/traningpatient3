package com.example.traningpatient3.presentation.feature.patients

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.traningpatient3.databinding.FragmentPatientsBinding
import com.example.traningpatient3.presentation.feature.adapter.PatientsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PatientsFragment : Fragment() {

    private lateinit var binding: FragmentPatientsBinding

    private val viewModel: PatientsViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPatientsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.patientsStateFlow.collect { response ->
                if (response.isNotEmpty())
                    binding.recyclerView.adapter = PatientsAdapter(response)
            }
        }
        lifecycleScope.launch {
            viewModel.patientsLoadingStateFlow.collect { show ->
                binding.progressCircular.isVisible = show
            }
        }
        lifecycleScope.launch {
            viewModel.patientsErrorStateFlow.collect { response ->
                Log.d("TAG00", response.toString())
            }
        }

    }
}