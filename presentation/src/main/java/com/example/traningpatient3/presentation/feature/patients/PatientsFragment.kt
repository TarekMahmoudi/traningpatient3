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
import androidx.navigation.fragment.findNavController
import com.example.traningpatient3.presentation.R
import com.example.traningpatient3.presentation.databinding.FragmentPatientsBinding
import com.example.traningpatient3.presentation.feature.adapter.PatientsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PatientsFragment : Fragment() {

    private lateinit var binding: FragmentPatientsBinding

    private val viewModel: PatientsViewModel by viewModels()
    private lateinit var adapter : PatientsAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPatientsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initObserver()
        initListener()
    }

    private fun initAdapter() {
        adapter = PatientsAdapter()
        binding.recyclerView.adapter = adapter
    }

    private fun initListener() {
        binding.addPerson.setOnClickListener {
            findNavController().navigate(R.id.addPatientFragment)
        }
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getPatients()
            lifecycleScope.launch{
                delay(3000)
                binding.swipeRefresh.isRefreshing = false
            }
        }
    }

    private fun initObserver(){
           lifecycleScope.launch {
               viewModel.patientsStateFlow.collect { response ->
                   if (response.isNotEmpty())
                       adapter.submitList(response)
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

