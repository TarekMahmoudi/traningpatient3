package com.example.traningpatient3.presentation.feature.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.traningpatient3.domain.model.add.AddPatientRemoteModel
import com.example.traningpatient3.domain.model.add.BodyAddPatientModel
import com.example.traningpatient3.domain.usecase.add.AddPatientUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class AddPatientViewModel @Inject constructor(private val addPatientUseCase: AddPatientUseCase) : ViewModel() {

    private val _addPatientsStateFlow : MutableStateFlow<AddPatientRemoteModel?> = MutableStateFlow(null)
    val addPatientsStateFlow = _addPatientsStateFlow.asStateFlow()

    private val _addPatientsErrorStateFlow : MutableStateFlow<Exception?> = MutableStateFlow(null)
    val addPatientsErrorStateFlow = _addPatientsErrorStateFlow.asStateFlow()

    private val _addPatientsLoadingStateFlow : MutableStateFlow<Boolean> = MutableStateFlow(false)
    val addPatientsLoadingStateFlow = _addPatientsLoadingStateFlow.asStateFlow()

    fun addPatient(bodyAddPatientModel: BodyAddPatientModel){

            viewModelScope.launch {
                try {
                    _addPatientsStateFlow.emit(addPatientUseCase(bodyAddPatientModel))

                }catch (e:Exception){
                    _addPatientsErrorStateFlow.emit(e)

                }
                _addPatientsLoadingStateFlow.emit(false)

            }




    }


}