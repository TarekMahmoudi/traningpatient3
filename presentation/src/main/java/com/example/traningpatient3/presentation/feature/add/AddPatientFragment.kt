package com.example.traningpatient3.presentation.feature.add

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.traningpatient3.domain.model.add.BodyAddPatientModel
import com.example.traningpatient3.presentation.databinding.FragmentAddPatientBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddPatientFragment : Fragment() {
    private lateinit var binding: FragmentAddPatientBinding
    private val viewModel: AddPatientViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddPatientBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObserver()
        initListener()
    }


    private fun initListener() {
        binding.materialButton.setOnClickListener {
            if (infoIsValid()) {
                val body = getInfoPatient()
                viewModel.addPatient(body)
            }
        }
    }

    private fun getInfoPatient(): BodyAddPatientModel {
        return BodyAddPatientModel(
            binding.editTextFullName.text.toString(),
            binding.editTextAddress.text.toString(),
            binding.editTextGender.text.toString(),
            binding.editTextBirthdate.text.toString(),
            binding.editTextMobile.text.toString(),
            binding.editTextEmail.text.toString(),
        )

    }

    private fun infoIsValid(): Boolean {
        var isValid = true

        if (binding.editTextFullName.text?.isEmpty() == true) {
            isValid = false
            binding.textFullName.error = "Name is Empty"

        }else{
            binding.textFullName.error = null
        }
        if (binding.editTextEmail.text?.isEmpty() == true) {
            isValid = false
            binding.textEmail.error = "Email is Empty"

        }else{
            binding.textBirthdate.error = null
        }
        if (binding.editTextAddress.text?.isEmpty() == true) {
            isValid = false
            binding.textAddress.error = "Address is Empty"

        }else{
            binding.textAddress.error = null
        }
        if (binding.editTextBirthdate.text?.isEmpty() == true) {
            isValid = false
            binding.textBirthdate.error = "Birthdate is Empty"

        }else{
            binding.textBirthdate.error = null
        }
        if (binding.editTextGender.text?.isEmpty() == true) {
            isValid = false
            binding.textGender.error = "gender is Empty"

        }else{
            binding.textGender.error = null
        }
        if (binding.editTextMobile.text?.isEmpty() == true) {
            isValid = false
            binding.textMobile.error = "mobile is Empty"

        }else{
            binding.textMobile.error = null
        }
        return isValid

    }

    private fun initObserver() {
        lifecycleScope.launch {
            viewModel.addPatientsStateFlow.collect { response ->
                if (response != null)
                    Toast.makeText(
                        requireContext(),
                        "patient added successfully :\n name ${response.name}\n createdAt : ${response.createdAt}",
                        Toast.LENGTH_SHORT
                    ).show()
            }
        }
        lifecycleScope.launch {
            viewModel.addPatientsLoadingStateFlow.collect { show ->
                binding.progressBar.isVisible = show
            }
        }
        lifecycleScope.launch {
            viewModel.addPatientsErrorStateFlow.collect { response ->
                if (response != null) {
                    Log.d("TAG00", response.toString())
                }
            }
        }
    }
}