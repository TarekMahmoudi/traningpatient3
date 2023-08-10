package com.example.traningpatient3.presentation.feature.patients

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.traningpatient3.domain.model.patients.PatientRemoteModel
import com.example.traningpatient3.domain.repo.patients.PatientsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class PatientsViewModel @Inject constructor (private val repository: PatientsRepository) : ViewModel() {

    private val _patientsStateFlow : MutableStateFlow<List<PatientRemoteModel>> = MutableStateFlow(emptyList())
    val patientsStateFlow: StateFlow<List<PatientRemoteModel>> = _patientsStateFlow

    private val _patientsErrorStateFlow : MutableStateFlow<Exception?> = MutableStateFlow(null)
    val patientsErrorStateFlow = _patientsErrorStateFlow.asStateFlow()

    private val _patientsLoadingStateFlow : MutableStateFlow<Boolean> = MutableStateFlow(true)
    val patientsLoadingStateFlow = _patientsLoadingStateFlow.asStateFlow()

    init{
    getPatients()
}
    fun getPatients() {
        viewModelScope.launch {
            try {
                _patientsStateFlow.emit(repository.getPatients())

            }catch (e:Exception){
                _patientsErrorStateFlow.emit(e)

            }
            _patientsLoadingStateFlow.emit(false)

        }


    }

}